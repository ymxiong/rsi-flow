package cc.eamon.open.flow.core.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/22 14:14
 */
public class MonitorBus {

    private String flowName;

    private String flowId;

    private final Logger logger = LoggerFactory.getLogger(MonitorBus.class);

    private final Queue<FlowStatistics> statisticsQueue = new LinkedList();

    private long totalTimeSpent = 0;

    public MonitorBus() {
    }

    public void setMonitorInfo(String flowName,String flowId) {
        if (this.flowName==null){
            this.flowName = flowName;
        }
        if (this.flowId==null){
            this.flowId = flowId;
        }
    }

    public void addFlowStatistics(FlowStatistics flowStatistics) {
        this.statisticsQueue.offer(flowStatistics);
        this.totalTimeSpent += flowStatistics.getTimeSpent();
    }


    public void printStatistics() {
        StringBuilder logStr = new StringBuilder();
        int suquence = 1;
        logStr.append("以下为名为["+flowName+"],ID为["+flowId+"]的Flow执行性能统计信息：\n");
        logStr.append("=============================================================================================================\n");
        logStr.append(String.format("%-10s","Sequence"));
        logStr.append(String.format("%-10s","Time(ms)" ));
        logStr.append(String.format("%-7s","%"));
        logStr.append(String.format("%-40s","FlowName"));
        logStr.append(String.format("%-30s","FlowClassName"));
        logStr.append(String.format("%-30s","FlowMethodName"));
        logStr.append("\n");
        for (FlowStatistics flowStatistics : statisticsQueue) {
            double percent= (((double) flowStatistics.getTimeSpent()) / totalTimeSpent )*100;
            DecimalFormat df = new DecimalFormat("#.00");
            logStr.append(String.format("%-10d",suquence++));
            logStr.append(String.format("%-10d",flowStatistics.getTimeSpent() ));
            logStr.append(String.format("%-7s",df.format(percent)));
            logStr.append(String.format("%-40s",flowStatistics.getFlowName()));
            logStr.append(String.format("%-30s",flowStatistics.getFlowComponentClassName()));
            logStr.append(String.format("%-30s",flowStatistics.getFlowMethodName()));
            logStr.append("\n");
        }

        logStr.append("==============================================================================================================\n");
        logger.info(logStr.toString());
    }
}



