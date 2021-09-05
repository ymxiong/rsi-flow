package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

import java.util.Date;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-09-04 15:45:25
 */
public class FlowConditionCode {

    @Flow(value = "FLOW_BIRTHDAY", retrieve = "type")
    public Integer FLOW_BIRTHDAY(String birthday) {
        Date date = new Date();
        if (Integer.parseInt(birthday.substring(0, 4)) + 18 > date.getYear()) {
            return 1;
        }
        return 0;
    }

    @Flow(value = "FLOW_CONDITION_CHILD", retrieve = "message")
    public String FLOW_CONDITION_CHILD() {
        return "您是小孩";
    }

    @Flow(value = "FLOW_CONDITION_OLD", retrieve = "message")
    public String FLOW_CONDITION_OLD() {
        return "您是成年人";
    }

}
