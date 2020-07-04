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
    public FlowCode getFlowCode() {
        return flowCode;
    }

    @Override
    public void execute(Object... args) {
        // TODO: OPTIMIZE PARAM AND CONTEXT REGISTER
        beforeExecute(args);
        flowCode.execute(args);
        afterExecute(args);
    }

    @Override
    public String getFlowName() {
        return flowCode.getFlowName();
    }

    @Override
    public Method getMethod() {
        return flowCode.getMethod();
    }

    public abstract void beforeExecute(Object... args);

    public abstract void afterExecute(Object... args);

}
