package cc.eamon.open.flow.core.check.structure;

import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/15
 * Detail: 流程节点，采用链表结构存储
 *         遍历之后得到一个List<FlowItem>
 *         然后对这个List中每条路径检查即可
 */
public class FlowItem {
    //前一个节点
    private FlowItem nextNode;

    //前一个节点
    private FlowItem preNode;

    //当前节点
    private RouteConfig curNode;

    public FlowItem getNextNode() {
        return nextNode;
    }

    public void setNextNode(FlowItem nextNode) {
        this.nextNode = nextNode;
    }

    public FlowItem getPreNode() {
        return preNode;
    }

    public void setPreNode(FlowItem preNode) {
        this.preNode = preNode;
    }

    public RouteConfig getCurNode() {
        return curNode;
    }

    public void setCurNode(RouteConfig curNode) {
        this.curNode = curNode;
    }

    /**
     * 检查该节点是否符合规范
     * @param checkResult
     * @return
     */
    protected void check(CheckResult checkResult){
        //TODO:递归检查，检查到结束节点，即为空时停止

        nextNode.check(checkResult);
    }

    /**
     * 检查该节点的入参是否都符合
     * @param checkResult
     * @return
     */
    protected void checkParams(CheckResult checkResult){
        //TODO:检查所有参数是否都符合结果


    }

    /**
     * 参数注入
     * @param checkResult
     * @return
     */
    protected void registerResult(CheckResult checkResult){
        //TODO:如果当前节点入参检查出了问题的时候，后续执行有待商榷
        //     idea1:结束当前路径的检查，用户只有解决了出错才能往后继续检查
        //     idea2:不注入结果，接着检查后续流程节点
    }

}
