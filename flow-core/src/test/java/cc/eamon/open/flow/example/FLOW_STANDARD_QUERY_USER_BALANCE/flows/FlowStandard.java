package cc.eamon.open.flow.example.FLOW_STANDARD_QUERY_USER_BALANCE.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;


public class FlowStandard {

    //去除姓名中的空格
    @Flow(value = FlowEnum.FLOW_STANDARD_NAME, retrieve = "userName")
    public String queryBalance(String userName) {
        return userName.replace(" ", "");
    }
}
