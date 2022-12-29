package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:02:12
 */
public class FlowScheduleCode {

    @Flow(value = "FLOW_CODE_SCHEDULE_EVENT_DELAY")
    public void scheduleEventDelay(String id) {
        System.out.println("FLOW_CODE_SCHEDULE_EVENT_DELAY SUCCESS");
        FlowEngine.instance().register("name", "FLOW_SCHEDULE_EVENT_DELAY SUCCESS");
    }

    @Flow(value = "FLOW_CODE_SCHEDULE_EVENT_DELAY")
    public void scheduleEventCron(String id) {
        System.out.println("FLOW_CODE_SCHEDULE_EVENT_CRON SUCCESS");
        FlowEngine.instance().register("name", "FLOW_SCHEDULE_EVENT_CRON SUCCESS");
    }

}
