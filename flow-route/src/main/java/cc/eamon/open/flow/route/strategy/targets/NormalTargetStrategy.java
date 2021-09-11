package cc.eamon.open.flow.route.strategy.targets;

import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-06-12 16:31:25
 */
public class NormalTargetStrategy implements TargetRouteStrategy {

    private Target target;


    public NormalTargetStrategy(Target target) {
        this.target = target;
    }

    @Override
    public String getTRType() {
        return TargetRouteStrategyEnums.NORMALSTRATEGY.getType();
    }

    @Override
    public Target getTarget() {
        return target;
    }


    @Override
    public RouteResult route(RouteResult routeResult) {
        return routeResult;
    }
}
