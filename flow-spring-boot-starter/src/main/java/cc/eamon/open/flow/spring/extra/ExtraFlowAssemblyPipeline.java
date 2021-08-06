package cc.eamon.open.flow.spring.extra;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.context.pipeline.BasePipeline;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowTypeNotSupportException;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:09:01
 */
public class ExtraFlowAssemblyPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ExtraFlowAssemblyPipeline.class);

    private Map<String, FlowConfig> flowConfigs;

    private Map<String, FlowHolder> flowHolders;

    public ExtraFlowAssemblyPipeline(int level, Map<String, FlowConfig> flowConfigs, Map<String, FlowHolder> flowHolders) {
        super(level);
        this.flowConfigs = flowConfigs;
        this.flowHolders = flowHolders;
    }

    @Override
    public void initializerSelf() {
        // TODO:未知，不知道有些时候会放入一个null元素,猜测是读取jar包
        logger.info("执行FlowAssemblyPipeline组装过程");
        for (FlowConfig flowConfig : flowConfigs.values()) {
            try {
                flowHolders.put(flowConfig.getId(), new FlowHolder(flowConfig));
            } catch (FlowCodeNotFoundException | FlowTypeNotSupportException e) {
                logger.info(e.getMessage());
            }
        }
        Initializer.addExtraFlowHolder(flowHolders);
    }
}