package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.config.flow.FlowConfig;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowTypeNotSupportException;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:09:01
 */
public class FlowAssemblyPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(FlowNodeHolder.class);

    private List<FlowConfig> flowConfigs;

    private Map<String, FlowHolder> flowHolders;

    public FlowAssemblyPipeline(Pipeline pre, List<FlowConfig> flowConfigs, Map<String, FlowHolder> flowHolders) {
        super(pre);
        this.flowConfigs = flowConfigs;
        this.flowHolders = flowHolders;
    }

    @Override
    public void initializerSelf() {
        for (FlowConfig flowConfig : flowConfigs) {
            try {
                flowHolders.put(flowConfig.getId(), new FlowHolder(flowConfig));
            } catch (FlowCodeNotFoundException | FlowTypeNotSupportException e) {
                logger.info(e.getMessage());
            }
        }
    }
}