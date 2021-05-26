package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;

public class FlowStrategy extends BaseParseStrategy {

    @Override
    public void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, FlowNodeConfig routeConfig) {
//        //递归检查元素
//        String flowName = routeConfig.getName();
//        FlowConfig flowConfig = configContext.getFlowConfigs().get(flowName);
//
//        BasicItem finalItem;
//        //TODO:封装该节点RouteConfig，对下一个节点执行封装
//        //TODO:嵌套检查
//        FlowRouteConfig target = routeConfig.getTargets().get(0);
//        FlowNodeConfig nextNode = configContext.getRouteById(target.getTarget());
//        ParseStrategy parseStrategy = ParseStrategyTypeEnum.getStrategy(nextNode.getType());
////        parseStrategy.parse(configContext,checkResult,finalItem,nextNode);
    }


}
