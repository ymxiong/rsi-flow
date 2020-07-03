package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 21:27:29
 */
public class SimpleFlowCode implements FlowCode {

    private Class<?> flowClass;

    private Method flowMethod;

    private Flow flowAnnotation;

    public SimpleFlowCode(Class<?> flowClass, Method flowMethod, Flow flowAnnotation) {
        this.flowClass = flowClass;
        this.flowMethod = flowMethod;
        this.flowAnnotation = flowAnnotation;
    }

    public Class<?> getFlowClass() {
        return flowClass;
    }

    public void setFlowClass(Class<?> flowClass) {
        this.flowClass = flowClass;
    }

    public Method getFlowMethod() {
        return flowMethod;
    }

    public void setFlowMethod(Method flowMethod) {
        this.flowMethod = flowMethod;
    }

    public Flow getFlowAnnotation() {
        return flowAnnotation;
    }

    public void setFlowAnnotation(Flow flowAnnotation) {
        this.flowAnnotation = flowAnnotation;
    }

    @Override
    public FlowCode getFlowCode() {
        return this;
    }

    @Override
    public String getFlowName() {
        return flowAnnotation.value();
    }

    // TODO: NEED OPTIMIZE CLASS INSTANCE
    @Override
    public void execute(Object... args) {
        Object target = null;
        try {
            target = flowClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Object returnValue = null;
        try {
            returnValue = flowMethod.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //存储流程代码运行结果
        //获取返回参数名
        String returnName = flowAnnotation.retrieve();
        //获取返回参数类型
        Class returnType = flowMethod.getReturnType();

        //TODO:返回类型匹配校验
        FlowContextHolder.get().getVariableTypeMap().put(returnName, returnType);
        FlowContextHolder.get().getVariableMap().put(returnName, returnValue);


    }

    @Override
    public Method getMethod() {
        return flowMethod;
    }
}
