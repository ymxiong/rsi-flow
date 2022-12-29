package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.FlowScheduleConfig;
import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowScheduleConfigException;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cn.edu.whu.zhuyuhan.scheduler.common.util.cron.CronUtils;
import cn.edu.whu.zhuyuhan.scheduler.task.ScheduleTaskRegistrar;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleCommonTask;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleOpen;
import cn.edu.whu.zhuyuhan.scheduler.task.model.ScheduleRegisterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;

public class FlowScheduleNodeHolder extends FlowNodeHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowScheduleNodeHolder.class);

    private FlowNodeHolder flowNodeHolder;

    FlowScheduleNodeHolder(FlowNodeConfig flowNodeConfig) throws Exception {
        super(flowNodeConfig);
        this.flowNodeHolder = new FlowNodeHolder(flowNodeConfig);
    }

    public void execute() throws Exception {
        FlowNodeConfig flowNodeConfig = getFlowNodeConfig();
        FlowScheduleConfig schedule = flowNodeConfig.getSchedule();
        if (schedule == null || StringUtils.isEmpty(schedule.getTime()))
            throw new FlowScheduleConfigException(flowNodeConfig.getId());
        if (StringUtils.isEmpty(schedule.getTaskName())) {
            schedule.setTaskName(flowNodeConfig.getReference());
        }
        FlowThread thread = new FlowThread(this.flowNodeHolder, new FlowContextExtractor(), new HashMap<>(), ExecutorHolder.get().getRoutes());
        ScheduleRegisterTask flowCodeTask;
        if (CronUtils.valid(schedule.getTime())) {
            flowCodeTask = new ScheduleCommonTask(
                    schedule.getTaskName(),
                    schedule.getTime(),
                    thread,
                    FlowContextHolder.get().retrieveVariables(),
                    flowNodeConfig.getId(),
                    null,
                    schedule.getAsync(),
                    schedule.getDistributed(),
                    schedule.getSpecial()
            );
        } else {
            flowCodeTask = new ScheduleCommonTask(
                    schedule.getTaskName(),
                    Long.parseLong(schedule.getTime()),
                    thread,
                    FlowContextHolder.get().retrieveVariables(),
                    flowNodeConfig.getId(),
                    null,
                    schedule.getAsync(),
                    schedule.getDistributed(),
                    schedule.getSpecial()
            );
        }
        ScheduleOpen scheduleOpen = new ScheduleOpen(flowCodeTask);
        ScheduleTaskRegistrar.register(scheduleOpen);
    }

}
