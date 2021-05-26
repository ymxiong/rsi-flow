package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.stereotype.definition.BaseFlowCodeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 19:32:58
 */
public class RemoteHttpCodeProxy extends BaseFlowCodeProxy {

    private static Logger logger = LoggerFactory.getLogger(RemoteHttpCodeProxy.class);

    private RemoteHttpCode flowCode;

    private long startTime;

    private long endTime;


    public RemoteHttpCodeProxy(RemoteHttpCode flowCode) {
        super(flowCode);
        this.flowCode = flowCode;
    }


    public void setFlowCode(RemoteHttpCode flowCode) {
        this.flowCode = flowCode;
    }


    @Override
    public void beforeExecute(Object... args) {
        startTime = System.currentTimeMillis();


    }

    @Override
    public void afterExecute(Object... args) {

    }
}
