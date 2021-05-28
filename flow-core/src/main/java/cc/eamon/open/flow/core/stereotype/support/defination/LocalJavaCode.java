package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 21:27:29
 */
public class LocalJavaCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(LocalJavaCode.class);

    private Class<?> clazz;

    private Method method;

    private Flow annotation;

    public LocalJavaCode(Class<?> clazz, Method method, Flow annotation) {
        this.clazz = clazz;
        this.method = method;
        this.annotation = annotation;
    }

    Class<?> getClazz() {
        return clazz;
    }


    @Override
    public String getName() {
        return annotation.value();
    }

    @Override
    public String getRetrieve() {
        return annotation.retrieve();
    }

    @Override
    public String getScope() {
        return annotation.scope();
    }

    // TODO: NEED OPTIMIZE CLASS INSTANCE
    @Override
    public Object execute(Object... args) throws Exception {
        try {
            Object target;
            if (Initializer.flowComponentMap.size()!=0){
                target=Initializer.flowComponentMap.get(clazz.getName());
            }else{
                target=clazz.newInstance();
            }

            return method.invoke(target, args);
        } catch (Exception e) {
            logger.info("Method in " + clazz.getName() + " named " + method.getName() + " invoke failed");
            Throwable nestedException = e.getCause();
            if (nestedException instanceof Exception) {
                throw (Exception) nestedException;
            }
            throw e;
        }

        // 获取返回参数类型
//        Class returnType = method.getReturnType();
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
    public List<TypeEnums> getParamTypes() {
        List<TypeEnums> types = new ArrayList<>();
        for (Class<?> parameterType : method.getParameterTypes()) {
            types.add(TypeEnums.getTypeByClass(parameterType));
        }
        return types;
    }

    Method getMethod() {
        return method;
    }
}
