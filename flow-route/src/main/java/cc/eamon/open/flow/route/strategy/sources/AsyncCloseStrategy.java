package cc.eamon.open.flow.route.strategy.sources;

import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.*;
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
public class AsyncCloseStrategy implements SourceRouteStrategy {

    private Source source;

    private List<Route> routes;

    // Initialize the engine.
    private VelocityEngine engine;

    public AsyncCloseStrategy(Source source) {
        this.source = source;
        routes=new ArrayList<>();
    }

    @Override
    public String getSRType() {
        return SourceRouteStrategyEnums.ASYNCCLOSESTRATEGY.getType();
    }

    @Override
    public Source getSource() {
        return source;
    }




    @Override
    public RouteResult route(Source source,Map<String, Object> variables) {
        AsyncCloseResult asyncCloseResult = null;
        List<Target> targets = ConditionHelper.JexlEngine(source, routes, variables);
        Assert.isTrue(targets.size() != 0 || RouteEnums.END.getId().equals(source.getId()),
                "流程条件有误，当前节点未能正确路由：" + source.getId());
        asyncCloseResult = new AsyncCloseResult(targets.get(0));
        return asyncCloseResult;
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
