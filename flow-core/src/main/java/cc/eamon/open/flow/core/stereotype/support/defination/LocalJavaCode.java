package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 21:27:29
 */
public class LocalJavaCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(LocalJavaCode.class);

    private Class<?> flowClass;

    private Method flowMethod;

    private Flow flowAnnotation;

    public LocalJavaCode(Class<?> flowClass, Method flowMethod, Flow flowAnnotation) {
        this.flowClass = flowClass;
        this.flowMethod = flowMethod;
        this.flowAnnotation = flowAnnotation;
    }

    Class<?> getFlowClass() {
        return flowClass;
    }


    @Override
    public String getName() {
        return flowAnnotation.value();
    }

    @Override
    public String getRetrieve() {
        return flowAnnotation.retrieve();
    }

    @Override
    public String getScope() {
        return flowAnnotation.scope();
    }

    // TODO: NEED OPTIMIZE CLASS INSTANCE
    @Override
    public Object execute(Object... args) throws Exception {
        try {
            Object target = flowClass.newInstance();
            return flowMethod.invoke(target, args);
        } catch (Exception e) {
            logger.info("Method in " + flowClass.getName() + " named " + flowMethod.getName() + " invoke failed");
            Throwable nestedException = e.getCause();
            if (nestedException instanceof Exception) {
                throw (Exception) nestedException;
            }
            throw e;
        }

        // 获取返回参数类型
//        Class returnType = flowMethod.getReturnType();
//        // TODO:返回类型匹配校验???
//        // 存储流程代码运行结果
//        // TODO:参数放到对应域
//        FlowResultConfig result = routeConfig.getResult();
//        // 获取返回参数名
//        String returnName = result.getName();
//        // 获取返回参数域
//        String returnScope = result.getScope();
//        FlowContextHolder.get().registerVariable(returnScope, returnName, returnValue, returnType);

    }

    @Override
    public Method getMethod() {
        return flowMethod;
    }
}
