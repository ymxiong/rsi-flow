package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.element.Source;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.util.WrapperClassUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @author: lucas
 * @date: 2020/7/12
 * @email: lucas@eamon.cc
 */
public class FlowNode extends BasicExecutable {

    /**
     * 流程节点对应的代码
     */
    private FlowCode flowCode;

    public FlowNode(String type, String name, String id, FlowHolder parentFlowHolder,FlowCode flowCode) {
        super(type, name, id, parentFlowHolder);
        this.flowCode=flowCode;
    }

    //TODO:执行该节点，然后路由执行后续节点
    @Override
    public void execute() {
        if(type.equals("start")) {
            executeNext((Source)this);
            return;
        }else if (type.equals("end")){
            return;
        }
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
            if (!parameterClasses[i].equals(FlowContextHolder.get().retrieveVariableType(parameterNames[i]))) {
                // TODO: 参数类型匹配
                if (parameterClasses[i].isPrimitive()){
                    WrapperClassUtils.getWrapperClass(parameterClasses[i]);
                    if (!WrapperClassUtils.getWrapperClass(parameterClasses[i]).equals(FlowContextHolder.get().retrieveVariableType(parameterNames[i]))){
                        throw new RuntimeException(parameterNames[i] + " does not match.");
                    }
                }else {
                    throw new RuntimeException(parameterNames[i] + " does not match.");
                }

            }
            Object obj = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
            if (obj == null) throw new RuntimeException(parameterNames[i] + " is missing.");
            args[i] = FlowContextHolder.get().retrieveVariable(parameterNames[i]);
        }

        flowCode.execute(args);
        executeNext((Source)this);
    }

}
