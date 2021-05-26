package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.monitor.FlowStatistics;
import cc.eamon.open.flow.core.stereotype.definition.BaseFlowCodeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 19:32:58
 */
public class LocalJavaCodeProxy extends BaseFlowCodeProxy {

    private static Logger logger = LoggerFactory.getLogger(LocalJavaCodeProxy.class);

    private LocalJavaCode flowCode;

    private long startTime;

    private long endTime;


    public LocalJavaCodeProxy(LocalJavaCode flowCode) {
        super(flowCode);
        this.flowCode = flowCode;
    }


    public void setFlowCode(LocalJavaCode flowCode) {
        this.flowCode = flowCode;
    }


    @Override
    public void beforeExecute(Object... args) {
        startTime = System.currentTimeMillis();


    }

    @Override
    public void afterExecute(Object... args) {
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        //logger.info("method named " + getMethod().getName()+ " in "+flowCode.getFlowClass().getName()+" executed " + totalTime + " ms.");
        FlowStatistics statistics = new FlowStatistics(getFlowName(), flowCode.getFlowClass().getSimpleName(), flowCode.getMethod().getName());
        statistics.setTimeSpent(totalTime);
        FlowContextHolder.get().getMonitorBus().addFlowStatistics(statistics);
    }
}
