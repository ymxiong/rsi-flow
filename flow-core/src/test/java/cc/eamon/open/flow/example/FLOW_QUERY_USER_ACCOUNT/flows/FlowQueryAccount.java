package cc.eamon.open.flow.example.FLOW_QUERY_USER_ACCOUNT.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnums;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.AccountService;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:42:19
 */
public class FlowQueryAccount {

    @Flow(value = FlowEnums.FLOW_QUERY_ACCOUNT_1)
    public Account queryAccount1(AccountService accountService, User user) {

        return accountService.getAccountByUserId(user.getId());
    }


    @Flow(value = FlowEnums.FLOW_QUERY_ACCOUNT_2)
    public Account queryAccount2(AccountService accountService, User user) {

        return accountService.getAccountByUserId(user.getId());
    }


}
