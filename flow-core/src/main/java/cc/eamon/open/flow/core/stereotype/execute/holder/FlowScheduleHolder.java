package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowScheduleConfig;
import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowScheduleConfigException;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowExecutableThread;
import cc.eamon.open.flow.model.enums.TypeEnums;
import cn.edu.whu.zhuyuhan.scheduler.common.util.cron.CronUtils;
import cn.edu.whu.zhuyuhan.scheduler.task.ScheduleTaskRegistrar;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleCommonTask;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleOpen;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleRegisterTask;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/1/27 4:20 下午
 **/
public class FlowScheduleHolder implements Executable {

    private String id;

    private FlowHolder flowHolder;

    private FlowScheduleConfig flowScheduleConfig;

    public FlowScheduleHolder(FlowHolder flowHolder) {
        this.flowHolder = flowHolder;
        this.flowScheduleConfig = flowHolder.getFlowConfig().getSchedule();
        String flowId = flowHolder.getFlowConfig().getId();
        if (this.flowScheduleConfig == null || StringUtils.isEmpty(this.flowScheduleConfig.getTime()))
            throw new FlowScheduleConfigException(flowId);
        if (StringUtils.isEmpty(this.flowScheduleConfig.getTaskName())) {
            this.flowScheduleConfig.setTaskName(flowId);
        }
        this.id = flowId;
    }

    @Override
    public List<TypeEnums> getParamsTypes() {
        return flowHolder.getParamsTypes();
    }

    @Override
    public void execute() throws Exception {
        FlowExecutableThread executableThread = new FlowExecutableThread(flowHolder, new FlowContextExtractor());
        ScheduleRegisterTask flowCodeTask;
        if (CronUtils.valid(this.flowScheduleConfig.getTime())) {
            flowCodeTask = new ScheduleCommonTask(
                    this.flowScheduleConfig.getTaskName(),
                    this.flowScheduleConfig.getTime(),
                    executableThread,
                    FlowContextHolder.get().retrieveVariables(),
                    id,
                    null,
                    this.flowScheduleConfig.getAsync(),
                    this.flowScheduleConfig.getDistributed(),
                    this.flowScheduleConfig.getSpecial()
            );
        } else {
            flowCodeTask = new ScheduleCommonTask(
                    this.flowScheduleConfig.getTaskName(),
                    Long.parseLong(this.flowScheduleConfig.getTime()),
                    executableThread,
                    FlowContextHolder.get().retrieveVariables(),
                    id,
                    null,
                    this.flowScheduleConfig.getAsync(),
                    this.flowScheduleConfig.getDistributed(),
                    this.flowScheduleConfig.getSpecial()
            );
        }
        ScheduleOpen scheduleOpen = new ScheduleOpen(flowCodeTask);
        ScheduleTaskRegistrar.register(scheduleOpen);
    }

}
