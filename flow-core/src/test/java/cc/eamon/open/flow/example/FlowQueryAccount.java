package cc.eamon.open.flow.example;

import cc.eamon.open.flow.core.Flow;
import cc.eamon.open.flow.core.entity.Account;
import cc.eamon.open.flow.core.entity.User;
import cc.eamon.open.flow.core.services.AccountService;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:42:19
 */
public class FlowQueryAccount {

    @Flow(value = FlowEnum.FLOW_QUERY_ACCOUNT, returned = "account")
    public Account queryAccount(AccountService accountService, User user){

        return accountService.getAccountByUserId(user.getId());
    }

}
