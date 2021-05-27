package cc.eamon.open.flow.core.stereotype.execute.support;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.stereotype.execute.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:29:34
 */
public class FlowExecutable implements Executable {

    private static Logger logger = LoggerFactory.getLogger(FlowExecutable.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;

    /**
     * 流程节点的配置
     */
    private FlowHolder flowHolder;

    /**
     * 当前该流程节点需要的入参以及他们所在Scope
     */
    private Map<String, FlowParamConfig> paramMap = new HashMap<>();

    /**
     * TODO: FLOW 注册校验
     * <p>
     * 当前该流程节点结果以及所在Scope
     */
    private Map<String, FlowResultConfig> resultMap = new HashMap<>();

    public FlowExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;

        // 校验flowConfig是否有

        // 装载params
        List<FlowParamConfig> params = flowNodeConfig.getParams();
        if (params != null) params.forEach(param -> paramMap.put(param.getName(), param));

        // 装载result
        List<FlowResultConfig> results = flowNodeConfig.getResults();
        if (results != null) results.forEach(result -> resultMap.put(result.getName(), result));
    }

    @Override
    public void execute() {
        this.flowHolder = Initializer.getFlowHolder(flowNodeConfig.getReference());
        if (flowHolder == null) throw new FlowNotFoundException(flowNodeConfig.getReference());
        flowHolder.execute();
    }

}
