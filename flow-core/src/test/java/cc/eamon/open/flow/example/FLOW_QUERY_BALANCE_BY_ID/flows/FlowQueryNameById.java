package cc.eamon.open.flow.example.FLOW_QUERY_BALANCE_BY_ID.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnums;
import cc.eamon.open.flow.example.remote.service.UserService;

public class FlowQueryNameById {

    @Flow(value = FlowEnums.FLOW_QUERY_NAME_BY_ID)
    public String getNameById(UserService userService, String id) {
        return userService.getUserById(id);
    }
}
