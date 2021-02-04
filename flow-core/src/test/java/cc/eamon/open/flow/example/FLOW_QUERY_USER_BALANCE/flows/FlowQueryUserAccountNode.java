package cc.eamon.open.flow.example.FLOW_QUERY_USER_BALANCE.flows;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;

public class FlowQueryUserAccountNode {

    @Flow(value = FlowEnum.FLOW_QUERY_BALANCE_NODE, retrieve = "account")
    public Account queryUserAccount() throws Exception {
        Account account = (Account) FlowEngine.instance()
                // 执行流程
                .run(FlowEnum.FLOW_QUERY_USER_ACCOUNT)
                // 取出执行结果
                .retrieve("testScope","account");

        return account;
    }
}
