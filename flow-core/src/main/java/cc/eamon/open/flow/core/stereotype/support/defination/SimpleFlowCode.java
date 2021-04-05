package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.spring.ComponentScaner;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.config.node.FlowNodeResultConfig;
import cc.eamon.open.flow.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.status.StatusException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 21:27:29
 */
public class SimpleFlowCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(SimpleFlowCode.class);

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
    public void execute(RouteConfig routeConfig, Object... args) throws Exception {
        Object target = null;

        if (FlowDispose.flowEnv) {
            try {
                target = flowClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                String info = "FlowComponent named " + flowClass.getName() + " not found. ";
                logger.info(info);
                throw new FlowNotFoundException(info);
            }
        } else {
            target = ComponentScaner.flowComponentMap.get(flowClass.getName());
            if (target == null) {
                String info = "FlowComponent named " + flowClass.getName() + " not found. ";
                logger.info(info);
                throw new FlowNotFoundException(info);
            }
        }

        Object returnValue = null;
        try {
            returnValue = flowMethod.invoke(target, args);
        } catch (Exception e) {
            Throwable mainException = e.getCause();
            if (mainException instanceof StatusException) {
                throw new StatusException(((StatusException) mainException).getCode(),mainException.getMessage(),((StatusException) mainException).getDetail());
            }
            logger.info("Method in " + flowClass.getName() + " named " + flowMethod.getName() + " invoke failed");
            logger.info(e.getMessage());
            throw e;
        }


        //获取返回参数类型
        Class returnType = flowMethod.getReturnType();

        //TODO:返回类型匹配校验???
        //存储流程代码运行结果
        //TODO:参数放到对应域
        FlowNodeResultConfig result = routeConfig.getResult();
        //获取返回参数名
        String returnName = result.getName();
        //获取返回参数域
        String returnScope = result.getScope();
        FlowContextHolder.get().registerVariable(returnScope, returnName, returnValue, returnType);

    }

    @Override
    public Method getMethod() {
        return flowMethod;
    }
}
