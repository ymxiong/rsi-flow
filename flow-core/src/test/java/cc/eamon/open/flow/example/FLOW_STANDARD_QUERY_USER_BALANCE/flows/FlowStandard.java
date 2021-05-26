package cc.eamon.open.flow.example.FLOW_STANDARD_QUERY_USER_BALANCE.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnums;


public class FlowStandard {

    // 去除姓名中的空格
    @Flow(value = FlowEnums.FLOW_STANDARD_NAME)
    public String queryBalance(String userName) {
        return userName.replaceAll(" ", "");
    }
}
