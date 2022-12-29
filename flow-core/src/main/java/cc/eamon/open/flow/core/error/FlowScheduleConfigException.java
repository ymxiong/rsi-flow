package cc.eamon.open.flow.core.error;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 17:11:27
 */
public class FlowScheduleConfigException extends RuntimeException {

    private static final long serialVersionUID = -6998042062909813019L;

    public FlowScheduleConfigException(String flowNodeId) {
        super("流程任务配置有误: " + flowNodeId + "请检查json");
    }

    public FlowScheduleConfigException(String flowNodeId, String message) {
        super("流程任务配置有误: " + flowNodeId + " " + message);
    }

    public FlowScheduleConfigException(String flowNodeId, String message, Throwable cause) {
        super(message, cause);
    }

    public FlowScheduleConfigException(String flowNodeId, Throwable cause) {
        super(cause);
    }

    public FlowScheduleConfigException(String flowNodeId, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
