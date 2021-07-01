package cc.eamon.open.flow.route;

import cc.eamon.open.flow.route.stereotype.*;
import cc.eamon.open.flow.route.strategy.RouteStrategy;
import cc.eamon.open.flow.route.strategy.SimpleStrategy;

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

    /**
     * 流程来源执行策略
     */
    private Map<String, RouteStrategy> sourceStrategyGroup = new HashMap<>();


    /**
     * 流程目标执行策略
     */
    private Map<String, RouteStrategy> targetStrategyGroup = new HashMap<>();


    private List<Condition> conditions = new ArrayList<>();

    public void load(Route... routes) {
        load(Arrays.asList(routes));
    }

    public void load(List<Route> routes) {
        RouteStrategy strategy = new SimpleStrategy(conditions, sourceRouteGroup, sources, targets);
        for (Route route : routes) {
            sources.putIfAbsent(route.getSource().getId(), route.getSource());
            targets.putIfAbsent(route.getTarget().getId(), route.getTarget());
            sourceRouteGroup.putIfAbsent(route.getSource().getId(), new ArrayList<>());
            sourceRouteGroup.computeIfPresent(route.getSource().getId(), ((s, sources) -> {
                sources.add(route);
                return sources;
            }));
            sourceStrategyGroup.put(route.getSource().getId(), strategy);
            targetRouteGroup.putIfAbsent(route.getTarget().getId(), new ArrayList<>());
            targetRouteGroup.computeIfPresent(route.getTarget().getId(), ((s, targets) -> {
                targets.add(route.getTarget());
                return targets;
            }));
            targetStrategyGroup.put(route.getTarget().getId(), strategy);
        }
    }

    public void load(Condition... conditions) {
        this.conditions.addAll(Arrays.asList(conditions));
    }

    public void load(Strategy... strategies) {
        for (Strategy strategy : strategies) {
            sourceStrategyGroup.put(strategy.getSource().getId(), strategy.getStrategy());
        }
    }

    public List<Target> execute(Source source) {
        return sourceStrategyGroup.get(source.getId()).route(source);
    }

}
