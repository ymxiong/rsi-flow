package cc.eamon.open.flow.route.strategy;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.AsynAllResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.*;
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
public class AsyncAllStrategy implements RouteStrategy {
    private Source source;

    private List<Route> routes;

    // Initialize the engine.
    private VelocityEngine engine;

    public AsyncAllStrategy(Source source) {
        this.source = source;
        routes=new ArrayList<>();
    }

    public AsyncAllStrategy(Route route) {
        this.source = route.getSource();
        routes = new ArrayList<>();
        routes.add(route);
    }

    @Override
    public String getType() {
        return RouteStrategyEnums.ASYNCALLSTRATEGY.getType();
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public RouteResult route(Source source, Map<String, Object> variables) {
        AsynAllResult asynAllResult = null;
        List<Target> targets = ConditionHelper.condition(source, routes, variables, engine);
        Assert.isTrue(targets.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());
        asynAllResult = new AsynAllResult(targets);
        return asynAllResult;
    }


    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }

    @Override
    public void setEngine(VelocityEngine engine) {
        this.engine = engine;
    }
}
