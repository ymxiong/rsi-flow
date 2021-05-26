package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowCodeNotFoundException;
import cc.eamon.open.flow.core.error.ParamNotExistException;
import cc.eamon.open.flow.core.error.ParamNotMatchException;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.utils.WrapperClassUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: lucas
 * Date: 2020/7/12
 * Email: lucas@eamon.cc
 */
public class FlowNodeHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowNodeHolder.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;

    /**
     * 流程代码
     */
    private FlowCode flowCode;

    /**
     * 当前该流程节点需要的入参以及他们所在Scope
     */
    private Map<String, FlowParamConfig> paramsMap = new HashMap<>();

    /**
     * 当前该流程节点结果以及所在Scope
     */
    private FlowResultConfig result;

    public FlowNodeHolder(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
        this.flowCode = Initializer.getFlowCodes().get(this.flowNodeConfig.getReference());
        if (flowCode == null) {
            logger.error("流程节点代码（reference）未找到");
            throw new FlowCodeNotFoundException();
        }
        // 封装params
        List<FlowParamConfig> params = flowNodeConfig.getParams();
        if (params != null) params.forEach(param -> paramsMap.put(param.getName(), param));

        // 封装result
        FlowResultConfig result = flowNodeConfig.getResult();
        if (result != null) this.result = result;
    }

    public void execute() {
        Method method = flowCode.getMethod();

        // 注入流程代码所需参数
        Class<?>[] parameterClasses = method.getParameterTypes();
        // 拿到参数名称以及类型
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        // 注入参数
        Assert.notNull(parameterNames, "方法参数名为空");
        int parameterNum = parameterNames.length;
        Object[] args = new Object[parameterNum];

        for (int i = 0; i < parameterNames.length; i++) {
            FlowParamConfig param = paramsMap.get(parameterNames[i]);
            Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), parameterNames[i]);
            Class<?> requiredParType = parameterClasses[i];
            if (variable == null) {
                String errorInfo = "Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not exist in scope of " + param.getScope() + " in flowContext.";
                logger.info(errorInfo);
                throw new ParamNotExistException(errorInfo);
            }
            // 参数类型匹配，包含父子类类型检查
            Class contextParType = variable.getClass();
            if (!requiredParType.isAssignableFrom(variable.getClass())) {
                // 检查基础类型与包装类冲突的问题
                if (requiredParType.isPrimitive()) {
                    WrapperClassUtils.getWrapperClass(requiredParType);
                    if (!WrapperClassUtils.getWrapperClass(requiredParType).isAssignableFrom(contextParType)) {
                        String errorInfo = "Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
                                ". The parameter type is " + contextParType;
                        logger.info(errorInfo);
                        throw new ParamNotMatchException(errorInfo);
                    }
                } else {
                    try {
                        // 解决Map类包装成实体类的问题
                        args[i] = JSON.parseObject(JSON.toJSONString(FlowContextHolder.get().retrieveVariable(param.getScope(), parameterNames[i])), requiredParType);
                    } catch (Exception e) {
                        String errorInfo = "Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
                                ". The parameter type is " + contextParType;
                        logger.info(errorInfo);
                        throw new ParamNotMatchException(errorInfo);
                    }
                }
            }

            if (args[i] == null) {
                args[i] = FlowContextHolder.get().retrieveVariable(param.getScope(), parameterNames[i]);
            }
        }

        try {
            Object value = flowCode.execute(args);
            FlowContextHolder.get().registerVariable(result.getScope(), result.getName(), value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return flowNodeConfig.getId();
    }

    public String getType() {
        return flowNodeConfig.getType();
    }

    public String getName() {
        return flowNodeConfig.getName();
    }

    public String getReference() {
        return flowNodeConfig.getReference();
    }

    public List<FlowParamConfig> getParams() {
        return flowNodeConfig.getParams();
    }

    public FlowResultConfig getResult() {
        return flowNodeConfig.getResult();
    }
}
