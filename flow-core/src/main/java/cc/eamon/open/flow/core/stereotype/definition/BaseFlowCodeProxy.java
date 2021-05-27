package cc.eamon.open.flow.core.stereotype.definition;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 19:35:56
 */
public abstract class BaseFlowCodeProxy implements FlowCode {

    private FlowCode flowCode;

    public BaseFlowCodeProxy(FlowCode flowCode) {
        this.flowCode = flowCode;
    }

    @Override
    public Object execute(Object... args) throws Exception {
        // TODO: OPTIMIZE PARAM AND CONTEXT REGISTER
        beforeExecute(args);
        Object value = flowCode.execute(args);
        afterExecute(args);
        return value;
    }

    @Override
    public String getName() {
        return flowCode.getName();
    }

    @Override
    public String getRetrieve() {
        return flowCode.getRetrieve();
    }

    @Override
    public String getScope() {
        return flowCode.getScope();
    }

    @Override
    public Method getMethod() {
        return flowCode.getMethod();
    }

    public abstract void beforeExecute(Object... args);

    public abstract void afterExecute(Object... args);

}
