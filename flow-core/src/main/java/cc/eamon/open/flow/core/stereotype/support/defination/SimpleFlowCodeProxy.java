package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.stereotype.definition.BaseFlowCodeProxy;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 19:32:58
 */
public class SimpleFlowCodeProxy extends BaseFlowCodeProxy {

    private SimpleFlowCode flowCode;

    public SimpleFlowCodeProxy(SimpleFlowCode flowCode) {
        super(flowCode);
        this.flowCode = flowCode;
    }


    public void setFlowCode(SimpleFlowCode flowCode) {
        this.flowCode = flowCode;
    }


    @Override
    public void beforeExecute(Object... args) {

    }

    @Override
    public void afterExecute(Object... args) {

    }
}
