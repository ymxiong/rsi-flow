package cc.eamon.open.flow.example.FLOW_INSUFFICIENT_PARAMETERS.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.UserService;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:42:19
 */
public class FlowQueryUser {

    @Flow(value = FlowEnum.FLOW_QUERY_USER_1, retrieve = "user")
    public User queryUser1(UserService userService, String missingName) {

        return userService.getUserByName(missingName);
    }


}
