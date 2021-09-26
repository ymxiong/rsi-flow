package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowRuntimeException;
import cc.eamon.open.flow.core.error.ParamNotExistException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import cc.eamon.open.flow.model.ModelEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:29:34
 */
public class EventExecutable implements Executable {

    private static Logger logger = LoggerFactory.getLogger(EventExecutable.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;

    /**
     * 流程代码
     */
    private FlowCode flowCode;

    public EventExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
        this.flowCode = Initializer.getFlowCodes().get(this.flowNodeConfig.getReference());
        if (flowCode == null) {
            logger.error("流程节点代码" + flowNodeConfig.getReference() + "未找到");
            throw new FlowCodeNotFoundException();
        }
    }

    @Override
    public List<TypeEnums> getParamsTypes() {
        return flowCode.getParamTypes();
    }

    @Override
    public void execute() {
        // 注入流程代码所需参数
        Object[] args = new Object[flowNodeConfig.getParams().size()];

        for (int i = 0; i < flowNodeConfig.getParams().size(); i++) {
            FlowParamConfig param = flowNodeConfig.getParams().get(i);
            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
            if (variable == null) {
                String errorInfo = "Error occurs when invoke " + flowNodeConfig.getId() + ".The nested problem is " + param.getName() + " does not exist in scope of " + param.getScope() + " in flowContext.";
                logger.info(errorInfo);
                throw new ParamNotExistException(errorInfo);
            }
            Object convertedVariable = ModelEngine.check(param.getType(), variable);
            if (!variable.equals(convertedVariable))
                FlowContextHolder.get().registerVariable(param.getScope(), param.getName(), convertedVariable);
            args[i] = convertedVariable;
        }
        try {
            //TODO: 修改参数注入配置
            Object value = flowCode.execute(args);
            if (flowNodeConfig.getResults().size() >= 1) {
                String scope = flowNodeConfig.getResults().get(flowNodeConfig.getResults().size() - 1).getScope();
                String retrieve = flowNodeConfig.getResults().get(flowNodeConfig.getResults().size() - 1).getName();
                FlowContextHolder.get().registerVariable(scope, retrieve, value);
            }
        } catch (Exception e) {
            throw new FlowRuntimeException(flowNodeConfig.getId());
        }
    }
}


