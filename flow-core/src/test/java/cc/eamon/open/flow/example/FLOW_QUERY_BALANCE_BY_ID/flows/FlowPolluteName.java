package cc.eamon.open.flow.example.FLOW_QUERY_BALANCE_BY_ID.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;

public class FlowPolluteName {

    @Flow(value = FlowEnum.FLOW_POLLUTE_NAME, retrieve = "userName")
    public String polluteName(String userName){
        return "      "+userName+" ";
    }
}
