package cc.eamon.open.flow.example.FLOW_QUERY_USER_ACCOUNT.flows;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.service.AccountService;

public class FlowQueryBalance {

    @Flow(value = FlowEnum.FLOW_QUERY_BALANCE, retrieve = "balance")
    public int queryBalance(AccountService accountService, Account account){
        return accountService.getBalance();
    }
}
