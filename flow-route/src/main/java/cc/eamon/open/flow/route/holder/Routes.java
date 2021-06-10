package cc.eamon.open.flow.route.holder;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-07 11:42:41
 */
public class Routes {

    private Map<String, Source> sources = new HashMap<>();

    private Map<String, Target> targets = new HashMap<>();

    /**
     * 流程来源路由容器
     */
    private Map<String, List<Route>> sourceRouteGroup = new HashMap<>();

    /**
     * 流程目标路由容器
     */
    private Map<String, List<Target>> targetRouteGroup = new HashMap<>();

    /**
     * TODO: 流程执行队列
     */
    private Queue<String> nodeQueue = new ConcurrentLinkedQueue<>();


    private List<Condition> conditions;

    public void load(Route... routes) {
        load(Arrays.asList(routes));
    }

    public void load(List<Route> routes) {
        for (Route route : routes) {
            sources.putIfAbsent(route.getSource().getId(), route.getSource());
            targets.putIfAbsent(route.getTarget().getId(), route.getTarget());
            sourceRouteGroup.putIfAbsent(route.getSource().getId(), new ArrayList<>());
            sourceRouteGroup.computeIfPresent(route.getSource().getId(), ((s, sources) -> {
                sources.add(route);
                return sources;
            }));
            targetRouteGroup.putIfAbsent(route.getTarget().getId(), new ArrayList<>());
            targetRouteGroup.computeIfPresent(route.getTarget().getId(), ((s, targets) -> {
                targets.add(route.getTarget());
                return targets;
            }));
        }
    }

    public List<Route> get(String groupId) {
        return sourceRouteGroup.get(groupId);
    }

    public List<String> executeQueue() {
        List<String> executeQueue = new ArrayList<>();
        while (nodeQueue.size() > 0) {
            String node = nodeQueue.poll();
            if (node == null) break;
            executeQueue.add(node);
            // 获取当前流程组
            List<Route> flowGroup = get(node);
            offerQueue(flowGroup);
        }
        return executeQueue;
    }

    public void offerQueue(List<Route> flowGroup) {
        // 执行器
        for (Route holder : flowGroup) {
            Target target = holder.getTarget();
            if (RouteEnums.END == RouteEnums.getConfigByName(target.getId())) continue;
            nodeQueue.offer(target.getId());
        }
    }

}
