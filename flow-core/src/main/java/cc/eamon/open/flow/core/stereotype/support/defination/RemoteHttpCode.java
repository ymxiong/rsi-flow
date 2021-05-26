package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 19:55:22
 */
public class RemoteHttpCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(LocalJavaCode.class);

    private Class<?> flowClass;

    private Method flowMethod;

    private Flow flowAnnotation;

    public RemoteHttpCode(Class<?> flowClass, Method flowMethod, Flow flowAnnotation) {
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
    public String getFlowName() {
        return flowAnnotation.value();
    }

    @Override
    public Object execute(Object... args) throws Exception {
        return null;
    }

    @Override
    public Method getMethod() {
        return flowMethod;
    }
}
