package cc.eamon.open.flow.example.FLOW_NOT_EXIST_COMPLEX_FLOW;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowInitializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.example.enums.FlowEnums;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    //复杂调用FlowHolder不存在【根据ID查姓名+污染姓名+姓名标准化+查询账户余额】
    @Test(expectedExceptions = FlowNotFoundException.class, groups = "error-test")
    public void testFlowEngine() throws Exception {
        // FlowInitializer.test();
        int balance = (int) FlowEngine.instance()
                .register(FlowConstants.SCOPE_TEST,"id", "123456")
                .register(FlowConstants.SCOPE_TEST,"userService", userService)
                .register(FlowConstants.SCOPE_TEST,"accountService", accountService)
                .run(FlowEnums.FLOW_NOT_EXIST_COMPLEX_FLOW)
                .retrieve(FlowConstants.SCOPE_TEST,"balance");
        Assert.assertEquals(balance, 12345);
        FlowEngine.instance().release();
    }
}
