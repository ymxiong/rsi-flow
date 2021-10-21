package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowParamHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowResultHolder;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    @Override
    public List<TypeEnums> getParamsTypes() {
        if (flowHolder == null)
            this.flowHolder = Initializer.getFlowHolder(flowNodeConfig.getReference());
        if (flowHolder == null) throw new FlowNotFoundException(flowNodeConfig.getReference());
        return flowHolder.getParamsTypes();
    }

    public FlowExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
    }

    @Override
    public void execute() throws Exception {
        if (flowHolder == null)
            this.flowHolder = Initializer.getFlowHolder(flowNodeConfig.getReference());
        if (flowHolder == null) throw new FlowNotFoundException(flowNodeConfig.getReference());

        for (int i = 0; i < flowNodeConfig.getParams().size(); i++) {
            FlowParamConfig flowParamConfig = flowNodeConfig.getParams().get(i);
            FlowParamHolder flowParamHolder = flowHolder.getParams().get(i);
            Object variable = FlowContextHolder.get().retrieveVariable(flowParamConfig.getScope(), flowParamConfig.getName());
            FlowContextHolder.get().registerVariable(flowParamHolder.getScope(), flowParamHolder.getName(), variable);
        }
        flowHolder.execute();
        for (int i = 0; i < flowNodeConfig.getResults().size(); i++) {
            FlowResultConfig flowResultConfig = flowNodeConfig.getResults().get(i);
            FlowResultHolder flowResultHolder = flowHolder.getResults().get(i);
            Object variable = FlowContextHolder.get().retrieveVariable(flowResultHolder.getScope(), flowResultHolder.getName());
            FlowContextHolder.get().registerVariable(flowResultConfig.getScope(), flowResultConfig.getName(), variable);
        }
    }

}
