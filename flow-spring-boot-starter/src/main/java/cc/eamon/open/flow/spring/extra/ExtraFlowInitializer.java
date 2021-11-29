package cc.eamon.open.flow.spring.extra;


import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.context.pipeline.*;
import cc.eamon.open.flow.core.error.FlowException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.spring.extra.pipeline.ExtraCodeLoadPipeline;
import cc.eamon.open.flow.spring.extra.pipeline.ExtraConfigLoadPipeline;
import cc.eamon.open.flow.spring.extra.pipeline.ExtraFlowAssemblyPipeline;
import cc.eamon.open.flow.spring.extra.pipeline.ExtraJarLoadPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
public class ExtraFlowInitializer {

    private static Logger logger = LoggerFactory.getLogger(ExtraFlowInitializer.class);

    /**
     * 流程Map
     * 键为流程id
     * 值为该流程的Holder,其中包含了流程的配置信息以及流程所需要的流程代码配置
     */
    private static Map<String, FlowHolder> extraFlowHolders = new ConcurrentHashMap<>();

    /**
     * 流程配置键值
     */
    private static Map<String, FlowConfig> extraFlowConfigs = new ConcurrentHashMap<>();

    /**
     * 流程代码键值Map
     * 键为流程代码的名称
     * 值为对应的流程代码
     */
    private static Map<String, FlowCode> extraFlowCodes = new ConcurrentHashMap<>();


    private static PriorityQueue<Pipeline> priorityQueue = new PriorityQueue<>();

    public static void addPipeline(BasePipeline... basePipeline) {
        priorityQueue.addAll(Arrays.asList(basePipeline));
    }

    public static void loadExtraFlowFromFile(File extraJar, String[] flowBasePackages) throws Exception {
        loadExtraFlow(extraJar, flowBasePackages, new String[]{""});
    }

    public static void loadExtraFlowFromMultipartFile(MultipartFile file, String[] flowBasePackages) throws Exception {
        File extraJar = FileHelper.getFileFromMultipartFile(file);
        loadExtraFlow(extraJar, flowBasePackages, new String[]{""});
    }

    private static void loadExtraFlow(File extraJar, String[] flowBasePackages, String[] configBasePackages) throws Exception {
        addPipeline(new ExtraJarLoadPipeline(0, extraJar));
        addPipeline(new ExtraConfigLoadPipeline(1, extraFlowConfigs, configBasePackages));
        addPipeline(new ExtraCodeLoadPipeline(2, extraFlowCodes, flowBasePackages));
        addPipeline(new ExtraFlowAssemblyPipeline(100, extraFlowConfigs, extraFlowHolders));
        BasePipeline pipeline = null;
        while (priorityQueue.peek() != null) {
            pipeline = ((BasePipeline) priorityQueue.poll()).setPre(pipeline);
        }
        if (pipeline == null) throw new FlowException("extraPipeline装配错误");
        pipeline.initializer();

        logger.info(extraJar.getName() + "流程配置信息装配完成");
    }


}
