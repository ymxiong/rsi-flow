package cc.eamon.open.flow.example;

import cc.eamon.open.flow.core.Flow;
import cc.eamon.open.flow.core.entity.User;
import cc.eamon.open.flow.core.services.UserService;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:42:19
 */
public class FlowQueryUser {

    @Flow(value = FlowEnum.FLOW_QUERY_USER, returned = "user")
    public User queryUser(UserService userService, String userName){

        return userService.getUserByName(userName);
    }



}
