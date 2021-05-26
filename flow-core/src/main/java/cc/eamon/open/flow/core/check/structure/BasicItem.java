package cc.eamon.open.flow.core.check.structure;

import cc.eamon.open.flow.core.check.context.ContextHolder;
import cc.eamon.open.flow.core.check.context.ParamContext;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;

import java.util.List;

public class BasicItem {
    //前一个节点
    private BasicItem nextNode;

    //前一个节点
    private BasicItem preNode;

    //当前节点
    private FlowNodeConfig curNode;

    public BasicItem(FlowNodeConfig curNode) {
        this.curNode = curNode;
    }

    public BasicItem getNextNode() {
        return nextNode;
    }

    public void setNextNode(BasicItem nextNode) {
        this.nextNode = nextNode;
    }

    public BasicItem getPreNode() {
        return preNode;
    }

    public void setPreNode(BasicItem preNode) {
        this.preNode = preNode;
    }

    public FlowNodeConfig getCurNode() {
        return curNode;
    }

    public void setCurNode(FlowNodeConfig curNode) {
        this.curNode = curNode;
    }

    @Override
    public BasicItem clone() throws CloneNotSupportedException {
        BasicItem basicItem = (BasicItem) super.clone();
        if (this.preNode != null) {
            BasicItem preNode = this.preNode.clone();
            basicItem.preNode = preNode;
            preNode.nextNode = basicItem;
        }
        basicItem.setCurNode(this.curNode);
        return basicItem;
    }


    public CheckResult check(CheckResult checkResult) {
        //检查入参
        checkParams(checkResult);
        //检查出参
        registerResult(checkResult);
        if (nextNode != null) {
            nextNode.check(checkResult);
        }
        return checkResult;
    }


    /**
     * 检查该节点的入参是否都符合
     *
     * @param checkResult
     * @return
     */
    public void checkParams(CheckResult checkResult) {
        //TODO:检查所有参数是否都符合结果
        List<FlowParamConfig> params = this.getCurNode().getParams();
        for (FlowParamConfig param : params) {
            ParamContext paramContext = ContextHolder.get();
            paramContext.retrieveParam(this, param.getScope(), param.getName(), param.getType(), checkResult);
        }
    }

    /**
     * 参数注入
     *
     * @param checkResult
     * @return
     */
    public void registerResult(CheckResult checkResult) {
        //TODO:如果当前节点入参检查出了问题的时候，后续执行有待商榷
        //     idea1:结束当前路径的检查，用户只有解决了出错才能往后继续检查
        //     idea2:不注入结果，接着检查后续流程节点
        FlowResultConfig result = this.getCurNode().getResult();
        ParamContext paramContext = ContextHolder.get();
        paramContext.registerParam(this, result.getScope(), result.getName(), result.getType(), checkResult);
    }
}
