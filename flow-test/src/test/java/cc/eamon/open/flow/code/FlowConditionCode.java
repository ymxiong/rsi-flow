package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-09-04 15:45:25
 */
public class FlowConditionCode {

    @Flow(value = "FLOW_BIRTHDAY", retrieve = "type")
    public Integer FLOW_BIRTHDAY(String birthday) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date birthDate = format.parse(birthday);

        Calendar current = Calendar.getInstance();
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTime(birthDate);
        int year = current.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (year > 18) {
            return 1;
        } else if (year < 18) {
            return 0;
        }
        // 如果年相等，就比较月份
        int month = current.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH);
        if (month > 0) {
            return 1;
        } else if (month < 0) {
            return 0;
        }
        // 如果月也相等，就比较天
        int day = current.get(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH);
        return day >= 0 ? 1 : 0;
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
