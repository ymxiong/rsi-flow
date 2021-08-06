package cc.eamon.open.flow.core.context;


import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.context.pipeline.*;
import cc.eamon.open.flow.core.error.FlowException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
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
    private static Map<String, FlowConfig> flowConfigs = new ConcurrentHashMap<>();

    /**
     * 流程代码键值Map
     * 键为流程代码的名称
     * 值为对应的流程代码
     */
    private static Map<String, FlowCode> flowCodes = new ConcurrentHashMap<>();


    /**
     * 流程代码所在路径
     */
    private static String[] flowBasePackages = new String[]{"cc.eamon.open.flow"};

    /**
     * 流程代码所在路径
     */
    private static String[] configBasePackages = new String[]{""};


    private static PriorityQueue<Pipeline> priorityQueue = new PriorityQueue<>();

    public static void addPipeline(BasePipeline... basePipeline) {
        priorityQueue.addAll(Arrays.asList(basePipeline));
    }

    public static void init() {
        addPipeline(new ConfigLoadPipeline(1, flowConfigs, configBasePackages));
        addPipeline(new CodeLoadPipeline(2, flowCodes, flowBasePackages));
        addPipeline(new FlowAssemblyPipeline(100, flowConfigs, flowHolders));
        BasePipeline pipeline = null;
        while (priorityQueue.peek() != null) {
            pipeline = ((BasePipeline) priorityQueue.poll()).setPre(pipeline);
        }
        if (pipeline == null) throw new FlowException("pipline装配错误");
        pipeline.initializer();
        logger.info("流程配置信息装配完成");
    }


    public static FlowHolder getFlowHolder(String flowHolderId) {
        return flowHolders.get(flowHolderId);
    }

    public static Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }


    public static void addExtraFlow(Map<String, FlowConfig> extraFlowConfigs, Map<String, FlowCode> extraFlowCodes, Map<String, FlowHolder> extraFlowHolders) {
        flowConfigs.putAll(extraFlowConfigs);
        flowCodes.putAll(extraFlowCodes);
        flowHolders.putAll(extraFlowHolders);
    }

}
