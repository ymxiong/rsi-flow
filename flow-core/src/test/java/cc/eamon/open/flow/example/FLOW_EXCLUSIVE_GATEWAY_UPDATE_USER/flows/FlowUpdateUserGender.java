package cc.eamon.open.flow.example.FLOW_EXCLUSIVE_GATEWAY_UPDATE_USER.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.UserService;

public class FlowUpdateUserGender {

    @Flow(value = FlowEnum.FLOW_UPDATE_USER_GENDER, retrieve = "user")
    public User updateUserGender(UserService userService, User user, String newGender) {
        return userService.updateGender(user, newGender);
    }
}
