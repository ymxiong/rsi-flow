package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.entity.Account;
import cc.eamon.open.flow.core.services.AccountService;
import cc.eamon.open.flow.core.services.UserService;
import cc.eamon.open.flow.example.FlowEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:18:18
 */
public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    @Test
    public void testFlowEngine(){
        FlowEngine.init();

        Account account = (Account) FlowEngine.instance()
                .register("userName", "小朱")
                .register("userService", userService)
                .register("accountService", accountService)
                .run(FlowEnum.FLOW_QUERY_USER_ACCOUNT)
                .retrieve("account");


        FlowEngine.clear();

        Assert.assertEquals(account.getId(), "654321");

    }


}
