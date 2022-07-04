package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.context.pipeline.*;
import cc.eamon.open.flow.core.error.FlowException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.model.ModelEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置、代码装配
 * <p>
 * Author: lucas
 * Date: 2020/6/28
 * Email: lucas@eamon.cc
 */
public class Initializer {

    private static Logger logger = LoggerFactory.getLogger(Initializer.class);

    /**
     * 流程Map
     * 键为流程id
     * 值为该流程的Holder,其中包含了流程的配置信息以及流程所需要的流程代码配置
     */
    private Map<String, FlowHolder> flowHolders = new ConcurrentHashMap<>();

    /**
     * 流程配置键值
     */
    private Map<String, FlowConfig> flowConfigs = new ConcurrentHashMap<>();

    /**
     * 流程Node配置对应流程配置映射
     */
    private Map<FlowNodeConfig, FlowConfig> flowNodeConfigFlowConfigs = new ConcurrentHashMap<>();

    /**
     * 流程代码键值Map
     * 键为流程代码的名称
     * 值为对应的流程代码
     */
    private Map<String, FlowCode> flowCodes = new ConcurrentHashMap<>();


    /**
     * 流程代码所在路径
     */
    private String[] flowBasePackages = new String[]{"cc.eamon.open.flow"};

    /**
     * 流程代码所在路径
     */
    private String[] configBasePackages = new String[]{""};


    /**
     * 流程模型所在路径
     */
    private String[] modelConfigFiles = new String[]{""};


    private PriorityQueue<Pipeline> priorityQueue = new PriorityQueue<>();

    public void addPipeline(BasePipeline... basePipeline) {
        priorityQueue.addAll(Arrays.asList(basePipeline));
    }

    public void init() throws Exception {
        addPipeline(new ConfigLoadPipeline(1, flowConfigs, configBasePackages));
        addPipeline(new CodeLoadPipeline(2, flowCodes, flowBasePackages));
        addPipeline(new ModelLoadPipeline(3, modelConfigFiles, ModelEngine.flowModelConfigs));
        addPipeline(new ModelAssemblyPipeline(4, ModelEngine.flowModelConfigs, ModelEngine.flowModels));
        addPipeline(new FlowAssemblyPipeline(100, flowConfigs, flowHolders));
        BasePipeline pipeline = null;
        while (priorityQueue.peek() != null) {
            pipeline = ((BasePipeline) priorityQueue.poll()).setPre(pipeline);
        }
        if (pipeline == null) throw new FlowException("pipeline装配错误");
        pipeline.initializer();
        logger.info("流程配置信息装配完成");
    }


    public FlowHolder getFlowHolder(String flowHolderId) {
        return flowHolders.get(flowHolderId);
    }

    public Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }

    public Map<FlowNodeConfig, FlowConfig> getFlowNodeConfigFlowConfigs() {
        return flowNodeConfigFlowConfigs;
    }

    public void addFlowNodeConfigFlowConfig(Map<FlowNodeConfig, FlowConfig> extraFlowNodeConfigFlowConfigs) {
        flowNodeConfigFlowConfigs.putAll(extraFlowNodeConfigFlowConfigs);
    }


    public void addExtraFlowConfig(Map<String, FlowConfig> extraFlowConfigs) {
        flowConfigs.putAll(extraFlowConfigs);
        for (Map.Entry<String, FlowConfig> flowConfigEntry : extraFlowConfigs.entrySet()) {
            FlowConfig flowConfig = flowConfigEntry.getValue();
            for (FlowNodeConfig flowNodeConfig : flowConfig.getNodes()) {
                flowNodeConfigFlowConfigs.put(flowNodeConfig, flowConfig);
            }
        }
    }

    public void addExtraFlowCode(Map<String, FlowCode> extraFlowCodes) {
        flowCodes.putAll(extraFlowCodes);
    }

    public void addExtraFlowHolder(Map<String, FlowHolder> extraFlowHolders) {
        flowHolders.putAll(extraFlowHolders);
    }

}
