package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;

public class EventStrategy extends BaseParseStrategy {


    @Override
    public void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, FlowNodeConfig routeConfig) {
//        //普通节点，就注册信息即可
//        NodeItem nodeItem = new NodeItem(routeConfig);
//        nodeItem.setPreNode(preItem);
//        preItem.setNextNode(nodeItem);
//        //递归处理后续节点
//        List<FlowRouteConfig> targets = routeConfig.getTargets();
//        if (targets.size() != 1) {
//            CheckException exception = new CheckException(nodeItem, "flow node's next node must be one node.");
//        }
//        FlowRouteConfig target = routeConfig.getTargets().get(0);
//        FlowNodeConfig nextNode = configContext.getRouteById(target.getTarget());
//        ParseStrategy parseStrategy = ParseStrategyTypeEnum.getStrategy(nextNode.getType());
//        parseStrategy.parse(configContext, checkResult, nodeItem, nextNode);

    }

}
