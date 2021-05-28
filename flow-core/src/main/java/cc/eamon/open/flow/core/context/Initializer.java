package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.context.pipeline.CodeLoadPipeline;
import cc.eamon.open.flow.core.context.pipeline.ConfigLoadPipeline;
import cc.eamon.open.flow.core.context.pipeline.FlowAssemblyPipeline;
import cc.eamon.open.flow.core.context.pipeline.Pipeline;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
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
    private static Map<String, FlowHolder> flowHolders = new ConcurrentHashMap<>();

    /**
     * 流程配置键值
     */
    private static List<FlowConfig> flowConfigs = new ArrayList<>();

    /**
     * 流程代码键值Map
     * 键为流程代码的名称
     * 值为对应的流程代码
     */
    private static Map<String, FlowCode> flowCodes = new ConcurrentHashMap<>();


    /**
     * 流程配置所在路径
     */
    private static String[] configBasePackages = new String[]{};


    /**
     * 流程代码所在路径
     */
    private static String[] flowBasePackages = new String[]{"cc.eamon.open.flow"};


    public static void configPath(String[] configPackages, String[] flowPackages) {
        configBasePackages = configPackages;
        flowBasePackages = flowPackages;
    }

    public static void init() {
        // 0. 初始化环境
        // 1. 扫描所有flow配置
        Pipeline pipeline = new ConfigLoadPipeline(null, flowConfigs, configBasePackages);
        // 2. 扫描所有flow代码
        pipeline = new CodeLoadPipeline(pipeline, flowCodes, flowBasePackages);
        // TODO: 提供添加pipeline的能力

        // 3. 装配flow
        pipeline = new FlowAssemblyPipeline(pipeline, flowConfigs, flowHolders);

        // 4. 初始化
        pipeline.initializer();
        logger.info("流程配置信息装配完成");
    }


    public static FlowHolder getFlowHolder(String flowHolderId) {
        return flowHolders.get(flowHolderId);
    }

    public static Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }

    public static void setConfigBasePackages(String[] configBasePackages) {
        Initializer.configBasePackages = configBasePackages;
    }

    public static void setFlowBasePackages(String[] flowBasePackages) {
        Initializer.flowBasePackages = flowBasePackages;
    }
}
