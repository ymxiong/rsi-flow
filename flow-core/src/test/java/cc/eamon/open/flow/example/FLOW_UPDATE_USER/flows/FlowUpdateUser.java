package cc.eamon.open.flow.example.FLOW_UPDATE_USER.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.UserService;

public class FlowUpdateUser {

    @Flow(value = FlowEnum.FLOW_UPDATE_GET_ACCESS)
    public String getRole(Account account) {
        return account.getRole();
    }


    @Flow(value = FlowEnum.FLOW_UPDATE_USER_AGE)
    public User updateUserAge(UserService userService, User user, Integer newAge, String role) {
        if (role.equals("manager"))
            return userService.updateAge(user, newAge);
        return user;
    }
}
