//package cc.eamon.open.flow.core.route.execute;
//
//import cc.eamon.open.flow.core.route.Route;
//import cc.eamon.open.flow.core.route.execute.strategy.RouteStrategy;
//import cc.eamon.open.flow.core.route.execute.strategy.enums.StrategyTypeEnums;
//import cc.eamon.open.flow.core.route.structure.RouteModel;
//import cc.eamon.open.flow.core.route.structure.element.RouteElement;
//import cc.eamon.open.flow.core.route.structure.element.Source;
//import cc.eamon.open.flow.core.route.structure.element.Target;
//import cc.eamon.open.flow.config.flow.FlowNodeConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Author: eamon Email: eamon@eamon.cc Time: 2020-06-26 17:13:16
// */
//public class RouteEngine implements Route {
//
//    private static Logger logger = LoggerFactory.getLogger(RouteEngine.class);
//
//    private RouteModel routeModel;
//
//
//    public RouteEngine(List<FlowNodeConfig> routeConfigs) {
//        routeModel = new RouteModel(routeConfigs);
//    }
//
//    @Override
//    public List<Target> route(Source source) {
//        List<Target> targets = new ArrayList<>();
//        //找到对应ID的当前节点
//        RouteElement routeElement = routeModel.getElementById(source.getId());
//        //寻找对应策略
//        RouteStrategy strategy = StrategyTypeEnums.getStrategy(routeElement.getType());
//        if (strategy == null) {
//            logger.info("route strategy can't be null");
//            throw new RuntimeException("route strategy can't be null");
//        }
//        //获取下一节点的id和name，封装成Target
//        List<String> nextIds = strategy.findWay(routeElement);
//        if (nextIds != null) {
//            for (String nextId : nextIds) {
//                RouteElement targetElement = routeModel.getElementById(nextId);
//                RouteStrategy targetStrategy = StrategyTypeEnums.getStrategy(targetElement.getType());
//                if (targetStrategy == null) {
//                    logger.info("route strategy can't be null");
//                    throw new RuntimeException("route strategy can't be null");
//                }
//                targetStrategy.processTarget(targetElement, targets, routeModel);
//            }
//        }
//        return targets;
//    }
//
//    @Override
//    public List<Target> route(List<Source> sources) {
//        List<Target> targets = new ArrayList<>();
//        for (Source source : sources) {
//            targets.addAll(route(source));
//        }
//        return targets;
//    }
//
//    @Override
//    public Target getStartNode() {
//        return routeModel.getStartNode();
//    }
//
//}
