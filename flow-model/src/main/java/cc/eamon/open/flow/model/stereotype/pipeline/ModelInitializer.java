package cc.eamon.open.flow.model.stereotype.pipeline;


import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.core.context.pipeline.*;
import cc.eamon.open.flow.core.error.FlowException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.model.definition.FlowModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模型装配
 * <p>
 * Author: lucas
 * Date: 2020/6/28
 * Email: lucas@eamon.cc
 */
public class ModelInitializer {

    private static Logger logger = LoggerFactory.getLogger(ModelInitializer.class);

    private static Map<String, ModelConfig> flowModelConfigs = new HashMap<>();

    private static Map<String, FlowModel> flowModels = new HashMap<>();

    /**
     * 流程代码所在路径
     */
    private static String[] modelConfigFiles = new String[]{""};

    private static PriorityQueue<Pipeline> priorityQueue = new PriorityQueue<>();

    public static void addPipeline(BasePipeline... basePipeline) {
        priorityQueue.addAll(Arrays.asList(basePipeline));
    }

    public static void init() {
        addPipeline(new ModelLoadPipeline(1, modelConfigFiles, flowModelConfigs));
        addPipeline(new ModelAssemblyPipeline(2, flowModelConfigs, flowModels));
        BasePipeline pipeline = null;
        while (priorityQueue.peek() != null) {
            pipeline = ((BasePipeline) priorityQueue.poll()).setPre(pipeline);
        }
        if (pipeline == null) throw new FlowException("Model pipeline装配错误");
        pipeline.initializer();
        logger.info("Flow Model装配完成");
    }


}
