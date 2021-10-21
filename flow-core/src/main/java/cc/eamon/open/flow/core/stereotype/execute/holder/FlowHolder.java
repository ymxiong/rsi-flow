package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.flow.FlowRouteStrategyConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowNodeDuplicateDefineException;
import cc.eamon.open.flow.core.error.ParamNotExistException;
import cc.eamon.open.flow.core.error.ResultNotExistException;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.route.Routes;
import cc.eamon.open.flow.route.basic.EndNode;
import cc.eamon.open.flow.route.basic.StartNode;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.*;
import cc.eamon.open.flow.route.strategy.sources.SourceRouteStrategyEnums;
import cc.eamon.open.flow.route.strategy.targets.TargetRouteStrategyEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        // 流程节点结构化容器
        Map<String, FlowNodeHolder> nodes = new HashMap<>();

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
        for (FlowRouteStrategyConfig strategyConfig : this.flowConfig.getStrategies()) {
            Source source = nodes.get(strategyConfig.getSource());
            Target target = nodes.get(strategyConfig.getTarget());
            if (source != null) {
                SourceRouteStrategy routeStrategy = SourceRouteStrategyEnums.routeStrategyInit(strategyConfig.getStrategy(), source);
                FlowRouteRouteStrategyHolder holder = new FlowRouteRouteStrategyHolder(strategyConfig, source, routeStrategy);
                routes.loadStrategy(holder);
            } else {
                TargetRouteStrategy targetRouteStrategy = TargetRouteStrategyEnums.targetStrategyInit(strategyConfig.getStrategy(), target);
                FlowRouteRouteStrategyHolder holder = new FlowRouteRouteStrategyHolder(strategyConfig, target, targetRouteStrategy);
                routes.loadTargetStrategy(holder);
            }
        }


        // 初始化流程路由
        for (FlowRouteConfig route : this.flowConfig.getRoutes()) {
            Condition condition = new Condition() {
                @Override
                public String getId() {
                    return route.getSource() + "-" + route.getTarget();
                }

                @Override
                public String getExpression() {
                    return route.getCondition();
                }
            };

            Source sourceNode = nodes.get(route.getSource());
            if (sourceNode == null) sourceNode = new StartNode();
            Target targetNode = nodes.get(route.getTarget());
            if (targetNode == null) targetNode = new EndNode();
            FlowRouteHolder holder = new FlowRouteHolder(route, sourceNode, targetNode, condition);
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
    public void execute() throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        for (FlowParamConfig param : flowConfig.getParams()) {
            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
            if (variable == null) throw new ParamNotExistException(param.getName());
            Object convertedVariable = ModelEngine.check(param.getType(), variable);
            if (!variable.equals(convertedVariable))
                FlowContextHolder.get().registerVariable(param.getScope(), param.getName(), convertedVariable);
            paramMap.put(param.getName(), convertedVariable);
        }
        execute(RouteEnums.START::getId, paramMap);

        // 检查执行结果是否给出
        for (FlowResultHolder result : results) {
            Object variable = FlowContextHolder.get().retrieveVariable(result.getScope(), result.getName());
            if (variable == null && result.getNotNull())
                throw new ResultNotExistException(flowConfig.getId(), result.getScope(), result.getName());
        }
    }

    private void execute(Source source, Map<String, Object> paramMap) throws Exception {
        ExecutorHolder.get().setRoutes(routes);
        ExecutorHolder.get().addExecuteNode(source);
        ExecutorHolder.get().execute(paramMap);
        ExecutorHolder.get().recoverRoutes();
    }

    public List<FlowParamHolder> getParams() {
        return params;
    }

    public List<FlowResultHolder> getResults() {
        return results;
    }
}


