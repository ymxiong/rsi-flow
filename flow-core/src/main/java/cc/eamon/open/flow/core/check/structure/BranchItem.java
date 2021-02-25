package cc.eamon.open.flow.core.check.structure;

import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.config.TargetConfig;

public class BranchItem extends BasicItem {
    //当前节点
    private TargetConfig targetConfig;

    public BranchItem(RouteConfig curNode, TargetConfig targetConfig) {
        super(curNode);
        this.targetConfig = targetConfig;
    }

    @Override
    public CheckResult check(CheckResult checkResult) {
        //检查入参
        checkCondition(checkResult);
        //检查出参
        if (super.getNextNode() != null) {
            super.getNextNode().check(checkResult);
        }
        return checkResult;
    }

    public void checkCondition(CheckResult checkResult) {
        //条件检测
    }

}
