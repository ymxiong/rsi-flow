package cc.eamon.open.flow.example.FLOW_QUERY_USER_BALANCE;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    //复杂线性编排测试【查询账户余额】
    @Test(groups = "test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        int balance = (int) FlowEngine.instance()
                .register("testScope","userName", "小朱")
                .register("testScope","userService", userService)
                .register("testScope","accountService", accountService)
                .run(FlowEnum.FLOW_QUERY_USER_BALANCE)
                .retrieve("testScope","balance");
        Assert.assertEquals(balance, 12345);
    }

}
