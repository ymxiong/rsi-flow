package cc.eamon.open.flow.spring.stereotype.support.defination;

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
public class SpringCodeProxy extends BaseFlowCodeProxy {

    private static Logger logger = LoggerFactory.getLogger(SpringCodeProxy.class);

    private SpringCode flowCode;

    private long startTime;

    public SpringCodeProxy(SpringCode flowCode) {
        super(flowCode);
        this.flowCode = flowCode;
    }


    @Override
    public void beforeExecute(Object... args) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterExecute(Object... args) {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("method named " + flowCode.getMethod().getName() + " in " + flowCode.getClazz().getName() + " executed " + totalTime + " ms.");
        FlowStatistics statistics = new FlowStatistics(getName(), flowCode.getClazz().getSimpleName(), flowCode.getMethod().getName());
        statistics.setTimeSpent(totalTime);
        FlowContextHolder.get().getMonitorBus().addFlowStatistics(statistics);
    }
}
