package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.InitializerEnums;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowNodeParamNotExistException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.model.enums.TypeEnums;
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
        this.flowCode = InitializerEnums.DEFAULT.getInitializer().getFlowCodes().get(this.flowNodeConfig.getReference());
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
    public void execute() throws Exception {

        FlowEngine.instance().setFlowScope(InitializerEnums.DEFAULT.getInitializer().getFlowNodeConfigFlowConfigs().get(flowNodeConfig).getId());

        // 注入流程代码所需参数
        Object[] args = new Object[flowNodeConfig.getParams().size()];

        for (int i = 0; i < flowNodeConfig.getParams().size(); i++) {
            FlowParamConfig param = flowNodeConfig.getParams().get(i);
            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
            Object convertedVariable = ModelEngine.build(param.getName(), param.getType(), variable, FlowContextHolder.get().retrieveVariables(param.getScope()));
            if (null == convertedVariable) {
                if (param.getNotnull() == null || param.getNotnull()) {
                    String errorInfo = "Error occurs when invoke " + flowNodeConfig.getId() + ".The nested problem is " + param.getName() + " does not exist in scope of " + param.getScope() + " in flowContext.";
                    logger.info(errorInfo);
                    throw new FlowNodeParamNotExistException(flowNodeConfig.getId(), param.getScope(), param.getName(), errorInfo);
                } else {
                    Object defaultValue = param.getDefaultValue();
                    if (defaultValue != null) {
                        convertedVariable = defaultValue;
                    } else {
                        args[i] = null;
                        continue;
                    }
                }
            }
            FlowContextHolder.get().registerVariable(param.getScope(), param.getName(), convertedVariable);
            args[i] = convertedVariable;
        }
        try {
            flowCode.execute(args);
        } catch (Exception e) {
            logger.info("流程节点：" + flowNodeConfig.getId() + " 执行错误，错误信息为:" + e.getMessage());
            throw e;
        }
    }

}

