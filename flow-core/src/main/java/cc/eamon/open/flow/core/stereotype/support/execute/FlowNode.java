package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.util.WrapperClassUtils;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @author: lucas
 * @date: 2020/7/12
 * @email: lucas@eamon.cc
 */
public class FlowNode extends BasicExecutable {

    private static Logger logger_ = LogManager.getLogger(FlowNode.class);
    /**
     * 流程节点对应的代码
     */
    private FlowCode flowCode;

    public FlowNode(String type, String name, String id, FlowHolder parentFlowHolder, FlowCode flowCode) {
        super(type, name, id, parentFlowHolder);
        this.flowCode = flowCode;
    }

    //TODO:执行该节点，然后路由执行后续节点
    @Override
    public void execute() throws Exception{
        Method method = flowCode.getMethod();
        // 注入流程代码所需参数
        // 拿到参数名称以及类型
        Class<?>[] parameterClasses = method.getParameterTypes();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        try {
            // 注入参数
            assert parameterNames != null;
            int parameterNum = parameterNames.length;
            Object[] args = new Object[parameterNum];

            for (int i = 0; i < parameterNum; i++) {

                Class contextParType = FlowContextHolder.get().retrieveVariableType(parameterNames[i]);
                Class<?> requiredParType = parameterClasses[i];
                // 参数在上下文中不存在
                if (contextParType == null) {
                    logger_.info("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not exist in flowContext.");
                    throw new RuntimeException("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not exist in flowContext.");
                } else {
                    // 参数类型匹配，包含父子类类型检查
                    if (!requiredParType.isAssignableFrom(contextParType)) {
                        // 检查基础类型与包装类冲突的问题
                        if (requiredParType.isPrimitive()) {
                            WrapperClassUtils.getWrapperClass(requiredParType);
                            if (!WrapperClassUtils.getWrapperClass(requiredParType).isAssignableFrom(contextParType)) {
                                logger_.info("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
                                        ". The parameter type is " + contextParType);
                                throw new RuntimeException("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + WrapperClassUtils.getWrapperClass(requiredParType) +
                                        ". The parameter type is " + contextParType);
                            }
                        } else {
                            try {
                                // 解决Map类包装成实体类的问题
                                args[i] = JSON.parseObject(JSON.toJSONString(FlowContextHolder.get().retrieveVariable(parameterNames[i])), requiredParType);
                            } catch (Exception e) {
                                logger_.info("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + requiredParType +
                                        ". The parameter type is " + contextParType);
                                throw new RuntimeException("Error occurs when invoke " + method.getName() + "().The nested problem is " + parameterNames[i] + " does not match. Require type is " + requiredParType +
                                        ". The parameter type is " + contextParType);
                            }
                        }
                    }
                }

                if (args[i] == null) {
                    args[i] = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
                }
            }

            flowCode.execute(args);
            executeNext((Source) this);
        }catch (Exception e){
            throw e;
        }
    }

}
