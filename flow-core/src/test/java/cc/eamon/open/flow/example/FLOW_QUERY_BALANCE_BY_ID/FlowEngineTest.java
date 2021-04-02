package cc.eamon.open.flow.example.FLOW_QUERY_BALANCE_BY_ID;

import cc.eamon.open.flow.config.constants.FlowConstants;
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

    //复杂嵌套编排4【根据ID查姓名+污染姓名+姓名标准化+查询账户余额】
    @Test(groups = "test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        int balance = (int) FlowEngine.instance()
                .register(FlowConstants.SCOPE_TEST,"id", "123456")
                .register(FlowConstants.SCOPE_TEST,"userService", userService)
                .register(FlowConstants.SCOPE_TEST,"accountService", accountService)
                .run(FlowEnum.FLOW_QUERY_BALANCE_BY_ID)
                .retrieve(FlowConstants.SCOPE_TEST,"balance");
        Assert.assertEquals(balance, 12345);
        FlowEngine.instance().release();
    }
}
