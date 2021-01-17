package cc.eamon.open.flow.core.stereotype.definition;

import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;

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
    public void execute(RouteConfig routeConfig, Object... args) throws Exception {
        // TODO: OPTIMIZE PARAM AND CONTEXT REGISTER
        beforeExecute(args);
        try {
            flowCode.execute(routeConfig,args);
        }catch (Exception e){
            throw e;
        }
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
