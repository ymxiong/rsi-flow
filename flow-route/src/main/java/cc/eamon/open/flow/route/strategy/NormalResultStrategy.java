package cc.eamon.open.flow.route.strategy;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.NormalResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Route;
import cc.eamon.open.flow.route.stereotype.RouteStrategy;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import cc.eamon.open.flow.route.util.ConditionHelper;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class NormalResultStrategy implements RouteStrategy {

    private Source source;

    private List<Route> routes;

    // Initialize the engine.
    private VelocityEngine engine;


    public NormalResultStrategy(Source source) {
        this.source = source;
        routes = new ArrayList<>();
    }


    public void setEngine(VelocityEngine engine) {
        this.engine = engine;
    }

    @Override
    public String getType() {
        return RouteStrategyEnums.NORMALRESULTSTRATEGY.getType();
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public RouteResult route(Source source, Map<String, Object> variables) {
        List<Target> condition = ConditionHelper.condition(source, routes, variables, engine);
        Assert.isTrue(condition.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());
        NormalResult normalResult = new NormalResult(condition.get(0));
        return normalResult;
    }


    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }
}
