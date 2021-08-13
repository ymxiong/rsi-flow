package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowTypeNotSupportException;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:09:01
 */
public class FlowAssemblyPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(FlowAssemblyPipeline.class);

    private Map<String, FlowConfig> flowConfigs;

    private Map<String, FlowHolder> flowHolders;

    public FlowAssemblyPipeline(int level, Map<String, FlowConfig> flowConfigs, Map<String, FlowHolder> flowHolders) {
        super(level);
        this.flowConfigs = flowConfigs;
        this.flowHolders = flowHolders;
    }

    @Override
    public void initializerSelf() {
        // TODO:未知，不知道有些时候会放入一个null元素,猜测是读取jar包
        logger.info("执行FlowAssemblyPipeline组装过程");
        if (flowConfigs.size()==0) {
            logger.info("未找到flowConfig,无法组装配置");
            return;
        }
        for (FlowConfig flowConfig : flowConfigs.values()) {
            try {
                flowHolders.put(flowConfig.getId(), new FlowHolder(flowConfig));
            } catch (FlowCodeNotFoundException | FlowTypeNotSupportException e) {
                logger.info(e.getMessage());
            }
        }
    }
}