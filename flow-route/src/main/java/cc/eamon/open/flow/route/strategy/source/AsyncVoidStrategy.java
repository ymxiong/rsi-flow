package cc.eamon.open.flow.route.strategy.source;

import cc.eamon.open.flow.route.result.AsynVoidResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.*;
import org.apache.velocity.app.VelocityEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class AsyncVoidStrategy implements SourceRouteStrategy {


    private Source source;

    private List<Route> routes;

    // Initialize the engine.
    private VelocityEngine engine;

    public AsyncVoidStrategy(Source source) {
        this.source = source;
        routes=new ArrayList<>();
    }

    @Override
    public String getSRType() {
        return SourceRouteStrategyEnums.ASYNCVOIDSTRATEGY.getType();
    }

    @Override
    public Source getSource() {
        return source;
    }

    public void setEngine(VelocityEngine engine) {
        this.engine = engine;
    }

    @Override
    public RouteResult route(Source source,Map<String, Object> variables) {

        AsynVoidResult asynVoidResult = null;
        if (routes.size() == 1) {
//            asynVoidResult = new AsynVoidResult(routes.get(0).getTarget());
        }

        return asynVoidResult;
    }


    @Override
    public void addRoute(Route route) {
        routes.add(route);
    }
}
