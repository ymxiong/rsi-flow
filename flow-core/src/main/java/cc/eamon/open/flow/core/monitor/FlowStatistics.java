package cc.eamon.open.flow.core.monitor;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/22 14:11
 */
public class FlowStatistics {
    private String flowComponentClassName;

    private String flowName;

    private String flowMethodName;

    private long timeSpent;

    private long memorySpent;

    public FlowStatistics(String flowName,String flowComponentClassName, String flowMethodName) {
        this.flowComponentClassName = flowComponentClassName;
        this.flowName = flowName;
        this.flowMethodName = flowMethodName;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowMethodName() {
        return flowMethodName;
    }

    public void setFlowMethodName(String flowMethodName) {
        this.flowMethodName = flowMethodName;
    }

    public String getFlowComponentClassName() {
        return flowComponentClassName;
    }

    public void setFlowComponentClassName(String flowComponentClassName) {
        this.flowComponentClassName = flowComponentClassName;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public long getMemorySpent() {
        return memorySpent;
    }

    public void setMemorySpent(long memorySpent) {
        this.memorySpent = memorySpent;
    }
}
