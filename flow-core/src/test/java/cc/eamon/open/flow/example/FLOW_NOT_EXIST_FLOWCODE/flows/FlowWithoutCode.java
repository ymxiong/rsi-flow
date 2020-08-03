package cc.eamon.open.flow.example.FLOW_NOT_EXIST_FLOWCODE.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.AccountService;

public class FlowWithoutCode {

    //@Flow(value = FlowEnum.FLOW_WITHOUT_CODE, retrieve = "account")
    public Account queryAccount1(AccountService accountService, User user) {

        return accountService.getAccountByUserId(user.getId());
    }
}
