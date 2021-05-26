package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;

public class ExclusiveGateWayStrategy extends BaseParseStrategy {
    @Override
    public void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, FlowNodeConfig routeConfig) {
//
//        List<FlowRouteConfig> targets = routeConfig.getTargets();
//        //发散节点处理
//        //聚合节点处理
//        if (targets.size() > 1) {
//            //TODO:发散节点处理
//            //TODO：每条路都是一条单独的路
//            for (FlowRouteConfig target : targets) {
//                FlowNodeConfig nextNode = configContext.getRouteById(target.getTarget());
//                ParseStrategy parseStrategy = ParseStrategyTypeEnum.getStrategy(nextNode.getType());
//                BasicItem branchItem = null;
//                try {
//                    branchItem = preItem.clone();
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                }
//                parseStrategy.parse(configContext, checkResult, branchItem, nextNode);
//            }
//
//        } else if (targets.size() == 1) {
//            //TODO:聚合节点处理
//            //TODO:合并共同参数
//            //TODO:处理下一个节点
//            FlowRouteConfig target = routeConfig.getTargets().get(0);
//            FlowNodeConfig nextNode = configContext.getRouteById(target.getTarget());
//            ParseStrategy parseStrategy = ParseStrategyTypeEnum.getStrategy(nextNode.getType());
//            parseStrategy.parse(configContext, checkResult, preItem, nextNode);
//        } else {
//            //出错
//
//        }
    }

}
