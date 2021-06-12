package cc.eamon.open.flow.route.strategy;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Condition;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class SimpleStrategy implements RouteStrategy {

    /**
     * TODO: 流程执行队列
     */
    private Queue<String> executeQueue = new ConcurrentLinkedQueue<>();


    private List<Condition> conditions;

    private Map<String, Source> sources;

    private Map<String, Target> targets;

    /**
     * 流程来源路由容器
     */
    private Map<String, List<Route>> sourceRouteGroup;

    public SimpleStrategy(
            List<Condition> conditions,
            Map<String, List<Route>> sourceRouteGroup,
            Map<String, Source> sources,
            Map<String, Target> targets
    ) {
        this.conditions = conditions;
        this.sourceRouteGroup = sourceRouteGroup;
        this.sources = sources;
        this.targets = targets;
    }

    @Override
    public List<Target> route(Source source) {
        List<Target> targets = new ArrayList<>();
        offerQueue(sourceRouteGroup.get(source.getId()));
        for (String id : executeQueue()) {
            targets.add(this.targets.get(id));
        }
        return targets;
    }

    private List<String> executeQueue() {
        List<String> executeQueue = new ArrayList<>();
        while (this.executeQueue.size() > 0) {
            String source = this.executeQueue.poll();
            if (source == null) break;
            executeQueue.add(source);
            // 获取当前流程组
            offerQueue(sourceRouteGroup.get(source));
        }
        return executeQueue;
    }

    private void offerQueue(List<Route> routes) {
        // 执行器
        for (Route route : routes) {
            Target target = route.getTarget();
            if (RouteEnums.END == RouteEnums.getConfigByName(target.getId())) continue;
            if (!"true".equals(route.getCondition().getId()) && !conditions.contains(route.getCondition())) continue;
            executeQueue.offer(target.getId());
        }
    }

}
