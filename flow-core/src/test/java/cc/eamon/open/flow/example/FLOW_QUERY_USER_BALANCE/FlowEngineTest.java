package cc.eamon.open.flow.example.FLOW_QUERY_USER_BALANCE;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowInitializer;
import cc.eamon.open.flow.example.enums.FlowEnums;
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
        // FlowInitializer.test();
        int balance = (int) FlowEngine.instance()
                .register(FlowConstants.SCOPE_TEST,"userName", "小朱")
                .register(FlowConstants.SCOPE_TEST,"userService", userService)
                .register(FlowConstants.SCOPE_TEST,"accountService", accountService)
                .run(FlowEnums.FLOW_QUERY_USER_BALANCE)
                .retrieve(FlowConstants.SCOPE_TEST,"balance");
        Assert.assertEquals(balance, 12345);
        FlowEngine.instance().release();
    }

}
