package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.util.WrapperClassUtils;
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
    public void execute() {
        Method method = flowCode.getMethod();
        // 注入流程代码所需参数
        // 拿到参数名称以及类型
        Class<?>[] parameterClasses = method.getParameterTypes();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        // 注入参数
        assert parameterNames != null;
        int parameterNum = parameterNames.length;
        Object[] args = new Object[parameterNum];

        for (int i = 0; i < parameterNum; i++) {
            if (FlowContextHolder.get().retrieveVariableType(parameterNames[i]) == null) {
                logger_.info("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not exist in flowContext.");
                throw new RuntimeException("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not exist in flowContext.");
            } else {
                if (!parameterClasses[i].isAssignableFrom(FlowContextHolder.get().retrieveVariableType(parameterNames[i]))) {
//                if (!FlowContextHolder.get().retrieveVariableType(parameterNames[i]).isInstance(parameterClasses[i])) {
                    // TODO: 参数类型匹配
                    if (parameterClasses[i].isPrimitive()) {
                        WrapperClassUtils.getWrapperClass(parameterClasses[i]);
                        if (!WrapperClassUtils.getWrapperClass(parameterClasses[i]).isAssignableFrom(FlowContextHolder.get().retrieveVariableType(parameterNames[i]))) {
//                        if (!FlowContextHolder.get().retrieveVariableType(parameterNames[i]).isInstance(WrapperClassUtils.getWrapperClass(parameterClasses[i]))) {
                            logger_.info("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not match. Require type is "+WrapperClassUtils.getWrapperClass(parameterClasses[i])+
                                    ". The parameter type is "+FlowContextHolder.get().retrieveVariableType(parameterNames[i]));
                            throw new RuntimeException("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not match. Require type is "+WrapperClassUtils.getWrapperClass(parameterClasses[i])+
                                    ". The parameter type is "+FlowContextHolder.get().retrieveVariableType(parameterNames[i]));
                        }
                    } else {
                        logger_.info("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not match. Require type is "+parameterClasses[i]+
                                ". The parameter type is "+FlowContextHolder.get().retrieveVariableType(parameterNames[i]));
                        throw new RuntimeException("Error occurs when invoke "+method.getName()+"().The nested problem is "+parameterNames[i] + " does not match. Require type is "+parameterClasses[i]+
                                ". The parameter type is "+FlowContextHolder.get().retrieveVariableType(parameterNames[i]));
                    }
                }
            }

            args[i] = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
        }

        flowCode.execute(args);
        executeNext((Source) this);
    }

}
