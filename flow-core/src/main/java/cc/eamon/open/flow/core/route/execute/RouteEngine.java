package cc.eamon.open.flow.core.route.execute;

import cc.eamon.open.flow.core.route.Route;
import cc.eamon.open.flow.core.route.execute.strategy.RouteStrategy;
import cc.eamon.open.flow.core.route.execute.strategy.enums.StrategyTypeEnum;
import cc.eamon.open.flow.core.route.structure.RouteModel;
import cc.eamon.open.flow.core.route.structure.element.RouteElement;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
 */
public class RouteEngine implements Route {

    private RouteModel routeModel;


    public RouteEngine(List<RouteConfig> routeConfigs) {
        routeModel = new RouteModel(routeConfigs);
    }

    @Override
    public List<Target> route(Source source) {
        List<Target> targets = new ArrayList<>();
        //找到对应ID的当前节点
        RouteElement routeElement = routeModel.getElementById(source.getId());
        //寻找对应策略
        RouteStrategy strategy = StrategyTypeEnum.getStrategy(routeElement.getType());
        if (strategy == null)
            throw new RuntimeException("route strategy can't be null");
        //获取下一节点的id和name，封装成Target
        List<String> nextIds = strategy.findWay(routeElement);
        if (nextIds != null) {
            for (String nextId : nextIds) {
                RouteElement targetElement = routeModel.getElementById(nextId);
                RouteStrategy targetStrategy = StrategyTypeEnum.getStrategy(targetElement.getType());
                if (targetStrategy == null)
                    throw new RuntimeException("route strategy can't be null");
                targetStrategy.processTarget(targetElement, targets, routeModel);
            }
        }
        return targets;
    }

    @Override
    public List<Target> route(List<Source> sources) {
        List<Target> targets = new ArrayList<>();
        for (Source source : sources) {
            targets.addAll(route(source));
        }
        return targets;
    }

    @Override
    public Target getStartNode() {
        return routeModel.getStartNode();
    }

}
