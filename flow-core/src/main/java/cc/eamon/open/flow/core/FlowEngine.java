package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.DisposeContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.element.FlowDetail;
import org.springframework.core.DefaultParameterNameDiscoverer;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    public static void init() {
        FlowContextHolder.init();
    }

    public static void clear() {
        FlowContextHolder.clear();
    }

    public static FlowEngine instance() {
        // 保障同一线程仅有单一执行的流程引擎
        if (FlowContextHolder.get() != null && FlowContextHolder.get().getFlowEngine() == null) {
            synchronized (FlowEngine.class) {
                if (FlowContextHolder.get().getFlowEngine() == null) {
                    FlowContextHolder.get().setFlowEngine(new FlowEngine());
                }
            }

        }
        return FlowContextHolder.get().getFlowEngine();
    }

    private FlowEngine() {
    }

    /**
     * @param flowDetail
     * @return
     */
    public FlowEngine run(FlowDetail flowDetail) {

        //TODO:流程代码执行完成直接返回（不知道是不是应该在这部分做，可能是谢帅宇路由做）
        if (flowDetail == null) {
            return this;
        }

        //首先拿到该流程代码
        Method method = DisposeContext.getNodeMap().get(flowDetail.getCurNodeName());
        //注入流程代码所需参数
        //拿到参数名称以及类型
        Class<?>[] parameterClasses = method.getParameterTypes();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);
        //注入参数
        int parameterNum = parameterNames.length;
        Object[] args = new Object[parameterNum];

        for (int i = 0; i < parameterNum; i++) {
            if (!parameterClasses[i].equals(FlowContextHolder.get().getVariableTypeMap().get(parameterNames[i]))) {
                //TODO: 参数匹配
                System.out.println("参数不匹配");
            }
            args[i] = FlowContextHolder.get().getVariableMap().get(parameterNames[i]);
        }

        //TODO：执行流程代码(需要使用代理模式)
        Class clazz = method.getDeclaringClass();
        Object target = null;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Object returnValue = null;
        try {
            returnValue = method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //存储流程代码运行结果
        //获取返回参数名
        String returnName = method.getAnnotation(Flow.class).retrieve();
        //获取返回参数类型
        Class returnType = method.getReturnType();
        //TODO:返回类型匹配校验
        FlowContextHolder.get().getVariableTypeMap().put(returnName, returnType);
        FlowContextHolder.get().getVariableMap().put(returnName, returnValue);

        //TODO：接入谢帅宇的路由功能(应该是在代理工厂中接入)
        run(flowDetail.getNextNode());
        return this;
    }

    public FlowEngine run(String flowName) {

        return this;
    }

    /**
     * 注入参数
     *
     * @param paramName
     * @param paramValue
     * @return
     */
    public FlowEngine register(String paramName, Object paramValue) {
        //注入参数到上下文中
        FlowContextHolder.get().getVariableMap().put(paramName, paramValue);
        FlowContextHolder.get().getVariableTypeMap().put(paramName, paramValue.getClass());
        return this;
    }

    public FlowEngine release(Object... param) {
        return this;
    }

    /**
     * 取得参数
     *
     * @param paramName
     * @return
     */
    public Object retrieve(String paramName) {
        return FlowContextHolder.get().getVariableMap().get(paramName);
    }

}
