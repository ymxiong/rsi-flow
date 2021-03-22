package cc.eamon.open.flow.example.FLOW_STANDARD_QUERY_USER_BALANCE;

import cc.eamon.open.flow.core.FlowConstant;
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

    //多层复杂性编排【姓名标准化+查询账户余额】
    @Test(groups = "test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        int balance = (int) FlowEngine.instance()
                .register(FlowConstant.SCOPE_TEST,"userName", "    小   朱   ")
                .register(FlowConstant.SCOPE_TEST,"userService", userService)
                .register(FlowConstant.SCOPE_TEST,"accountService", accountService)
                .run(FlowEnum.FLOW_STANDARD_QUERY_USER_BALANCE)
                .retrieve(FlowConstant.SCOPE_TEST,"balance");
        Assert.assertEquals(balance, 12345);
        FlowEngine.instance().release();
    }

}
