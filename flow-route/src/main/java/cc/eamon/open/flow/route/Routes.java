package cc.eamon.open.flow.route;

import cc.eamon.open.flow.route.enums.ConditionEnums;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.NormalResult;
import cc.eamon.open.flow.route.stereotype.*;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.strategy.source.SourceRouteStrategyEnums;
import cc.eamon.open.flow.route.strategy.target.TargetRouteStrategyEnums;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.springframework.util.Assert;

import java.io.StringWriter;
import java.util.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-07 11:42:41
 */
public class Routes {

    // Initialize the engine.
    private VelocityEngine engine;

    /**
     * 流程来源路由容器
     */
    private Map<String, List<Route>> sourceRouteGroup = new HashMap<>();

    /**
     * 流程目标路由容器
     */
    private Map<String, List<Route>> targetRouteGroup = new HashMap<>();


    /**
     * 流程目标路由容器
     */
    private Map<String, SourceRouteStrategy> sourceStrategyGroup = new HashMap<>();


    /**
     * 流程目标路由容器
     */
    private Map<String, TargetRouteStrategy> targetStrategyGroup = new HashMap<>();


    public Routes() {
        engine = new VelocityEngine();
        engine.setProperty("runtime.log.invalid.references", false);
        engine.setProperty(Velocity.RESOURCE_LOADERS, "string");
        engine.setProperty("resource.loader.string.class", StringResourceLoader.class.getName());
        engine.init();
    }

    public void load(Route... routes) {
        load(Arrays.asList(routes));
    }

    public void load(List<Route> routes) {
        for (Route route : routes) {
            sourceRouteGroup.putIfAbsent(route.getSource().getId(), new ArrayList<>());
            sourceRouteGroup.computeIfPresent(route.getSource().getId(), ((s, sources) -> {
                sources.add(route);
                return sources;
            }));
            targetRouteGroup.putIfAbsent(route.getTarget().getId(), new ArrayList<>());
            targetRouteGroup.computeIfPresent(route.getTarget().getId(), ((s, targets) -> {
                targets.add(route);
                return targets;
            }));

            sourceStrategyGroup.putIfAbsent(route.getSource().getId(), SourceRouteStrategyEnums.routeStrategyInit(SourceRouteStrategyEnums.NORMALRESULTSTRATEGY.getType(), route.getSource()));
            sourceStrategyGroup.computeIfPresent(route.getSource().getId(), ((s, strategy) -> {
                strategy.setEngine(engine);
                strategy.addRoute(route);
                return strategy;
            }));

            targetStrategyGroup.putIfAbsent(route.getTarget().getId(), TargetRouteStrategyEnums.targetStrategyInit(SourceRouteStrategyEnums.NORMALRESULTSTRATEGY.getType(), route.getTarget()));
            targetStrategyGroup.computeIfPresent(route.getSource().getId(), ((t, strategy) -> {
                return strategy;
            }));


        }

    }


    public void loadStrategy(SourceRouteStrategy... strategies) {
        loadStrategy(Arrays.asList(strategies));
    }

    public void loadStrategy(List<SourceRouteStrategy> strategies) {
        for (SourceRouteStrategy routeStrategy : strategies) {
            SourceRouteStrategy routeResultStrategy = sourceStrategyGroup.putIfAbsent(routeStrategy.getSource().getId(), SourceRouteStrategyEnums.routeStrategyInit(routeStrategy.getSRType(), routeStrategy.getSource()));
            // TODO:报错
        }
    }


    public void loadTargetStrategy(TargetRouteStrategy... strategies) {
        loadTargetStrategy(Arrays.asList(strategies));
    }

    public void loadTargetStrategy(List<TargetRouteStrategy> strategies) {
        for (TargetRouteStrategy targetRouteStrategy : strategies) {
            targetStrategyGroup.putIfAbsent(targetRouteStrategy.getTarget().getId(), TargetRouteStrategyEnums.targetStrategyInit(targetRouteStrategy.getTRType(), targetRouteStrategy.getTarget()));
            // TODO:报错
        }
    }


    // 只有一个
    public List<Target> execute(Source source, Map<String, Object> variables) {
        List<Route> routes = sourceRouteGroup.get(source.getId());
        List<Target> targets = new ArrayList<>();

        Target defaultTarget = null;

        for (Route route : routes) {
            if (ConditionEnums.DEFAULT.getId().equals(route.getCondition().getExpression())) {
                defaultTarget = route.getTarget();
                continue;
            }
            // 校验condition
            // Add template to repository
            StringResourceRepository repository = StringResourceLoader.getRepository();
            repository.putStringResource(route.getCondition().getId(), route.getCondition().getExpression());

            // Set parameters
            VelocityContext context = new VelocityContext();

            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                context.put(entry.getKey(), entry.getValue().toString());
            }

            // Process the template
            StringWriter writer = new StringWriter();
            engine.getTemplate(route.getCondition().getId()).merge(context, writer);

            Boolean success = (Boolean) AviatorEvaluator.execute(writer.toString());
            if (success) {
                targets.add(route.getTarget());
            }
        }

        if (targets.size() == 0 && defaultTarget != null) {
            targets.add(defaultTarget);
        }

        Assert.isTrue(targets.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());

        return targets;
    }

    public RouteResult executeByStrategy(Source source, Map<String, Object> variables) {
        RouteResult routeResult = sourceStrategyGroup.get(source.getId()).route(source, variables);
        if (routeResult instanceof NormalResult) {
            routeResult = targetStrategyGroup.get(((NormalResult) routeResult).getNextNode().getId()).route(routeResult);
        }
        return routeResult;
    }

}
