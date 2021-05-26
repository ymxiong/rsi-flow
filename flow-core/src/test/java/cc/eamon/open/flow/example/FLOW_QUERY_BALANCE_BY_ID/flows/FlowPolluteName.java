package cc.eamon.open.flow.example.FLOW_QUERY_BALANCE_BY_ID.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnums;

public class FlowPolluteName {

    @Flow(value = FlowEnums.FLOW_POLLUTE_NAME)
    public String polluteName(String userName) {
        return "      " + userName + " ";
    }
}
