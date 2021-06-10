package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowNodeDuplicateDefineException;
import cc.eamon.open.flow.core.error.ParamNotExistException;
import cc.eamon.open.flow.core.error.ResultNotExistException;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.FlowNodeTypeEnums;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import cc.eamon.open.flow.route.holder.Routes;
import cc.eamon.open.flow.route.stereotype.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Routes routes = new Routes();

    /**
     * 流程参数容器
     */
    private List<FlowParamHolder> params = new ArrayList<>();

    /**
     * 流程结果容器
     */
    private List<FlowResultHolder> results = new ArrayList<>();

    /**
     * @param flowConfig 流程配置
     */
    public FlowHolder(FlowConfig flowConfig) {
        // 初始化流程配置
        this.flowConfig = flowConfig;

        // 默认流程域
        String defaultFlowScope = flowConfig.getId();

        // 初始化参数容器
        for (FlowParamConfig param : flowConfig.getParams()) {
            if (StringUtils.isEmpty(param.getScope())) {
                param.setScope(defaultFlowScope);
            }
            params.add(new FlowParamHolder(param));
        }

        // 初始化结果容器
        for (FlowResultConfig result : flowConfig.getResults()) {
            if (StringUtils.isEmpty(result.getScope())) result.setScope(defaultFlowScope);
            results.add(new FlowResultHolder(result));
        }

        // 初始化流程节点容器
        for (FlowNodeConfig node : this.flowConfig.getNodes()) {
            if (nodes.get(node.getId()) != null) throw new FlowNodeDuplicateDefineException();
            for (FlowParamConfig param : node.getParams()) {
                if (StringUtils.isEmpty(param.getScope())) param.setScope(defaultFlowScope);
            }
            for (FlowResultConfig result : node.getResults()) {
                if (StringUtils.isEmpty(result.getScope())) result.setScope(defaultFlowScope);
            }
            nodes.put(node.getId(), new FlowNodeHolder(node));
        }

        // 初始化流程路由
        for (FlowRouteConfig route : this.flowConfig.getRoutes()) {
            FlowRouteHolder holder = new FlowRouteHolder(route);
            routes.load(holder);
        }
    }

    @Override
    public List<TypeEnums> getParamsTypes() {
        List<TypeEnums> types = new ArrayList<>();
        for (FlowParamHolder param : params) {
            types.add(param.getType());
        }
        return types;
    }

    @Override
    public void execute() {
        // 检查参数是否提供
        for (FlowParamHolder param : params) {
            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
            if (variable == null) throw new ParamNotExistException(param.getName());
        }

        // 获取开始流程组
        List<Route> startGroup = routes.get(FlowNodeTypeEnums.START.getName());
        // 流程校验
        Assert.isTrue(startGroup != null && startGroup.size() > 0, "未定义开始流程组");
        // 提供执行数据
        routes.offerQueue(startGroup);
        // 提供执行
        List<String> nodeIds = routes.executeQueue();
        for (String nodeId : nodeIds) {
            nodes.get(nodeId).execute();
        }

        // 检查执行结果是否给出
        for (FlowResultHolder result : results) {
            Object variable = FlowContextHolder.get().retrieveVariable(result.getScope(), result.getName());
            if (variable == null)
                throw new ResultNotExistException(flowConfig.getId(), result.getScope(), result.getName());
        }
    }

    public List<FlowParamHolder> getParams() {
        return params;
    }

    public List<FlowResultHolder> getResults() {
        return results;
    }
}


