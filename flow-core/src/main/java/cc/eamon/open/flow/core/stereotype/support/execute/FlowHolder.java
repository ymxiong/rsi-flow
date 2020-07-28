package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.route.Route;
import cc.eamon.open.flow.core.route.execute.RouteEngine;
import cc.eamon.open.flow.core.route.structure.RouteModel;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.route.structure.element.Target;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;

import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:18:53
 */
public class FlowHolder implements FlowExecutable, Source, Target {

    private FlowConfig flowConfig;

    private String id;

    private Map<String, FlowCode> flowCodes;

    private Route route;

    private RouteModel routeModel;

    public FlowHolder(FlowConfig flowConfig, Map<String, FlowCode> flowCodes) {
        this.flowConfig = flowConfig;
        this.flowCodes = flowCodes;
        routeModel = new RouteModel(flowConfig.getRoutes());
        route = new RouteEngine(routeModel);
    }

    public String getFlowName() {
        return flowConfig.getName();
    }

    public Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }

    // TODO: NEED OPTIMIZE THREAD RUN
    @Override
    public void execute() {
        //获取开始节点
        Target start = routeModel.getStartNode();
        FlowNode startNode = new FlowNode(flowCodes.get(start.getName()),
                start.getId(), this);
        //执行自身节点
        startNode.execute();
        //执行下一个节点
        executeNext();
    }

    @Override
    public void executeNext(){
        //TODO:执行下一个节点
        List<Target> targets = this.getRoute().route(this);
        //没有目标了
        if (targets.isEmpty())
            return;
        if (targets.size() <= 1) {
            executeNext(targets.get(0));
        } else {
            executeNext(targets);
        }
    }

    //TODO:执行后续节点
    public void executeNext(Target target){
        //TODO:拿到的节点是FlowNode
        //FlowNode.execute()
        //TODO:拿到的节点是FlowHolder
        //FlowHolder.execute()
    }

    //TODO:执行后续节点
    public void executeNext(List<Target> targets){
        //TODO:分支的地方就会调用多线程
        //TODO:针对每一条业务路线都开启一个多线程来执行
        //TODO:主线程等待所有子线程完成
        //TODO:所有子线程变量放到父线程中
        //TODO:所有子线程执行完毕，网关闭合综合条件拿到下一个节点(怎么综合网关)

    }


    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return flowConfig.getName();
    }

    public Route getRoute() {
        return route;
    }


}


