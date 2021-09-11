package cc.eamon.open.flow.route.strategy.targets;

import cc.eamon.open.flow.route.result.AsyncCloseResult;
import cc.eamon.open.flow.route.result.NormalResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Target;
import cc.eamon.open.flow.route.stereotype.TargetRouteStrategy;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class CloseTargetRouteStrategy implements TargetRouteStrategy {


    private Target target;


    public CloseTargetRouteStrategy(Target target) {
        this.target = target;
    }

    @Override
    public String getTRType() {
        return TargetRouteStrategyEnums.CLOSETARGETSTRATEGY.getType();
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Override
    public RouteResult route(RouteResult routeResult) {
        AsyncCloseResult asyncCloseResult = new AsyncCloseResult(((NormalResult) routeResult).getNextNode());
        return asyncCloseResult;

    }
}
