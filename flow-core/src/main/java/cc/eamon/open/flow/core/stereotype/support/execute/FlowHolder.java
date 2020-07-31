package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.route.Route;
import cc.eamon.open.flow.core.route.execute.RouteEngine;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:18:53
 */

public class FlowHolder extends BasicExecutable {

    /**
     * 流程的配置
     */
    private FlowConfig flowConfig;


    /**
     * 流程的所有代码
     */
    private Map<String, FlowCode> flowCodes;

    /**
     * 流程的路由
     */
    private Route route;

    public FlowHolder(String type, String name, String id, FlowHolder parentFlowHolder,FlowConfig flowConfig, Map<String, FlowCode> flowCodes) {
        super(type, name, id, parentFlowHolder);
        this.flowConfig=flowConfig;
        this.flowCodes=flowCodes;
        route=new RouteEngine(flowConfig.getRoutes());
    }
    public Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }

    public Route getRoute() {
        return route;
    }

    public void setParentFlowHolder(FlowHolder parentFlowHolder) {
        super.parentFlowHolder = parentFlowHolder;
    }

    @Override
    public void execute() {
        //获取开始节点
        Target start = route.getStartNode();
        FlowNode startNode = new FlowNode(start.getType(),start.getName(),start.getId(),
                this,flowCodes.get(start.getName()));
        //执行自身节点
        startNode.execute();
        //执行下一个节点
        executeNext(this);
    }




}


