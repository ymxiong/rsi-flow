package cc.eamon.open.flow.config.flow;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class FlowScheduleConfig implements Serializable {

    private static final long serialVersionUID = -790595105386137380L;

    /**
     * 任务名称
     */
    @JSONField(ordinal = 1)
    private String taskName;

    /**
     * 触发时间（cron或者delay）
     */
    @JSONField(ordinal = 2)
    private String time;

    /**
     * 是否异步
     */
    @JSONField(ordinal = 3)
    private Boolean async = false;

    /**
     * 是否分布式
     */
    @JSONField(ordinal = 4)
    private Boolean distributed = true;

    /**
     * 是否特殊调度
     */
    @JSONField(ordinal = 5)
    private Boolean special = false;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }

    public Boolean getDistributed() {
        return distributed;
    }

    public void setDistributed(Boolean distributed) {
        this.distributed = distributed;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }
}
