package cc.eamon.open.flow.core.route.execute.strategy;

import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;

import java.util.ArrayList;
import java.util.List;

public class FlowStrategy implements RouteStrategy {
    @Override
    public List<String> findWay(RouteElement routeElement) {
        List<String> results = new ArrayList<>();
        //目标
        List<FlowRouteConfig> targets = routeElement.getTargetConfigs();
        //取第一个目标
        results.add(targets.get(0).getTarget());
        return results;
    }
}
