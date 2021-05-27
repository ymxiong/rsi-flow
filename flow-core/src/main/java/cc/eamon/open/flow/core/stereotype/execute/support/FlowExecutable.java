package cc.eamon.open.flow.core.stereotype.execute.support;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.stereotype.execute.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public FlowExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;

        // 校验flowConfig是否有
        
    }

    @Override
    public void execute() {
        this.flowHolder = Initializer.getFlowHolder(flowNodeConfig.getReference());
        if (flowHolder == null) throw new FlowNotFoundException(flowNodeConfig.getReference());
        flowHolder.execute();
    }

}
