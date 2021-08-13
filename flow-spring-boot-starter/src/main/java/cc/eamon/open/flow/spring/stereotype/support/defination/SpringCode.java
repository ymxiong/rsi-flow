package cc.eamon.open.flow.spring.stereotype.support.defination;

import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowComponentNotFoundException;
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
public class SpringCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(SpringCode.class);

    private Object component;

    private Method method;

    private Flow annotation;

    public SpringCode(Object component, Method method, Flow annotation) {
        if (component == null) throw new FlowComponentNotFoundException();
        this.component = component;
        this.method = method;
        this.annotation = annotation;
    }

    Class<?> getClazz() {
        return component.getClass();
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
            return method.invoke(component, args);
        } catch (Exception e) {
            logger.info("Method in " + getClazz().getName() + " named " + method.getName() + " invoke failed");
            Throwable nestedException = e.getCause();
            if (nestedException instanceof Exception) {
                throw (Exception) nestedException;
            }
            throw e;
        }
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
