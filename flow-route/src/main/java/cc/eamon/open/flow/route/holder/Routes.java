package cc.eamon.open.flow.route.holder;

import cc.eamon.open.flow.route.stereotype.Condition;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.*;

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


}
