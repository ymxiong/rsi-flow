package cc.eamon.open.flow.core.check.strategy;

import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;
import cc.eamon.open.flow.core.check.structure.EndItem;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;

public class EndEventStrategy extends BaseParseStrategy {


    @Override
    public void parse(ConfigContext configContext, CheckResult checkResult, BasicItem preItem, FlowNodeConfig routeConfig) {
        //TODO:如果是递归的
        EndItem endItem = new EndItem(routeConfig);
        endItem.setPreNode(preItem);
        preItem.setNextNode(endItem);
        //将路径加入路径集合
        BasicItem startItem = endItem;
        while (startItem.getPreNode() != null) {
            startItem = startItem.getPreNode();
        }
        checkResult.addPath(startItem);
    }
}
