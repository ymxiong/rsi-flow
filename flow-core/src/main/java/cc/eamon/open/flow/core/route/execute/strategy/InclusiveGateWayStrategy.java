package cc.eamon.open.flow.core.route.execute.strategy;

import cc.eamon.open.flow.core.route.structure.RouteModel;
import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.route.structure.element.Target;

import java.util.List;

public class InclusiveGateWayStrategy implements RouteStrategy {
    @Override
    public List<String> findWay(RouteElement routeElement) {
        return null;
    }

    @Override
    public void processTarget(RouteElement target, List<Target> targets, RouteModel routeModel) {

    }
}
