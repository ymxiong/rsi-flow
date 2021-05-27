package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.core.error.FlowNodeDuplicateDefineException;
import cc.eamon.open.flow.core.stereotype.execute.support.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.FlowNodeTypeEnums;
import cc.eamon.open.flow.core.stereotype.execute.node.FlowParamHolder;
import cc.eamon.open.flow.core.stereotype.execute.node.FlowResultHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:18:53
 */

public class FlowHolder implements Executable {

    private static Logger logger = LoggerFactory.getLogger(FlowHolder.class);

    /**
     * 流程的配置
     */
    private FlowConfig flowConfig;

    /**
     * 流程节点结构化容器
     */
    private Map<String, FlowNodeHolder> nodes = new HashMap<>();

    /**
     * 流程路由容器
     */
    private List<FlowRouteHolder> routes = new ArrayList<>();

    /**
     * 流程参数容器
     */
    private List<FlowParamHolder> params = new ArrayList<>();

    /**
     * 流程结果容器
     */
    private List<FlowResultHolder> results = new ArrayList<>();

    /**
     * 流程来源路由容器
     */
    private Map<String, List<FlowRouteHolder>> sourceRouteGroup = new HashMap<>();

    /**
     * TODO: 流程执行队列
     */
    private Queue<FlowNodeHolder> nodeQueue = new ConcurrentLinkedQueue<>();

    /**
     * @param flowConfig 流程配置
     */
    public FlowHolder(FlowConfig flowConfig) {
        // 初始化流程配置
        this.flowConfig = flowConfig;
        // 初始化流程节点
        for (FlowNodeConfig node : this.flowConfig.getNodes()) {
            if (nodes.get(node.getId()) != null) throw new FlowNodeDuplicateDefineException();
            nodes.put(node.getId(), new FlowNodeHolder(node));
        }
        // 初始化流程路由
        for (FlowRouteConfig route : this.flowConfig.getRoutes()) {
            FlowRouteHolder holder = new FlowRouteHolder(route);
            routes.add(holder);
            // 初始化源头路由
            sourceRouteGroup.putIfAbsent(holder.getSource(), new ArrayList<>());
            sourceRouteGroup.computeIfPresent(holder.getSource(), ((s, flowRouteHolders) -> {
                flowRouteHolders.add(holder);
                return flowRouteHolders;
            }));
        }
    }

    public FlowConfig getFlowConfig() {
        return flowConfig;
    }

    public Map<String, FlowNodeHolder> getNodes() {
        return nodes;
    }

    public List<FlowRouteHolder> getRoutes() {
        return routes;
    }

    @Override
    public void execute() {
        // 获取开始流程组
        List<FlowRouteHolder> startGroup = sourceRouteGroup.get(FlowNodeTypeEnums.START.getName());
        // 流程校验
        Assert.isTrue(startGroup != null && startGroup.size() > 0, "未定义开始流程组");
        // 提供执行数据
        offerQueue(startGroup);
        // 提供执行
        executeQueue();
    }

    private void executeQueue() {
        while (nodeQueue.size() > 0) {
            FlowNodeHolder node = nodeQueue.poll();
            if (node == null) return;
            node.execute();
            // 获取当前流程组
            List<FlowRouteHolder> flowGroup = sourceRouteGroup.get(node.getId());
            offerQueue(flowGroup);
        }
    }

    private void offerQueue(List<FlowRouteHolder> flowGroup) {
        // 执行器
        for (FlowRouteHolder holder : flowGroup) {
            String targetId = holder.getTarget();
            if (FlowNodeTypeEnums.END == FlowNodeTypeEnums.getConfigByName(targetId)) continue;
            FlowNodeHolder nodeHolder = nodes.get(targetId);
            nodeQueue.offer(nodeHolder);
        }
    }

}


