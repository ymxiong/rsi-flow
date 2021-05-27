package cc.eamon.open.flow.core.stereotype.execute.support;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.FlowRuntimeException;
import cc.eamon.open.flow.core.error.ParamNotExistException;
import cc.eamon.open.flow.core.error.ParamNotMatchException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
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
            logger.error("流程节点代码（reference）未找到");
            throw new FlowCodeNotFoundException();
        }
    }

    @Override
    public void execute() {
        // 注入流程代码所需参数
        List<Class<?>> parameterClasses = flowCode.getParameterTypes();

        // 参数匹配性校验
        if (parameterClasses.size() != flowNodeConfig.getParams().size())
            throw new ParamNotMatchException(flowNodeConfig.getId(), "");

        Object[] args = new Object[parameterClasses.size()];

        for (int i = 0; i < parameterClasses.size(); i++) {
            FlowParamConfig param = flowNodeConfig.getParams().get(i);

            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
            if (variable == null) {
                String errorInfo = "Error occurs when invoke " + flowNodeConfig.getId() + "().The nested problem is " + param.getName() + " does not exist in scope of " + param.getScope() + " in flowContext.";
                logger.info(errorInfo);
                throw new ParamNotExistException(errorInfo);
            }

            if (args[i] == null) {
                args[i] = variable;
            }
        }

        try {
            String scope = flowCode.getScope();
            String retrieve = flowCode.getRetrieve();
            Object value = flowCode.execute(args);
            FlowContextHolder.get().registerVariable(scope, retrieve, value);
        } catch (Exception e) {
            throw new FlowRuntimeException(flowNodeConfig.getId());
        }
    }
}


//            // 参数类型匹配，包含父子类类型检查
//            Class contextParType = variable.getClass();
//            Class<?> requiredParType = parameterClasses.get(i);
//            if (!requiredParType.isAssignableFrom(variable.getClass())) {
//                // 检查基础类型与包装类冲突的问题
//                if (requiredParType.isPrimitive()) {
//                    WrapperClassUtils.getWrapperClass(requiredParType);
//                    if (!WrapperClassUtils.getWrapperClass(requiredParType).isAssignableFrom(contextParType)) {
//                        String errorInfo = "Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
//                                ". The parameter type is " + contextParType;
//                        logger.info(errorInfo);
//                        throw new ParamNotMatchException(errorInfo, parameterNames[i]);
//                    }
//                } else {
//                    try {
//                        // 解决Map类包装成实体类的问题
//                        args[i] = JSON.parseObject(JSON.toJSONString(FlowContextHolder.get().retrieveVariable(param.getScope(), parameterNames[i])), requiredParType);
//                    } catch (Exception e) {
//                        String errorInfo = "Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
//                                ". The parameter type is " + contextParType;
//                        logger.info(errorInfo);
//                        throw new ParamNotMatchException(errorInfo, parameterNames[i]);
//                    }
//                }
//            }

