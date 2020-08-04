package cc.eamon.open.flow.example.FLOW_NOT_EXIST_COMPLEX_FLOW;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    //复杂调用FlowHolder不存在【根据ID查姓名+污染姓名+姓名标准化+查询账户余额】
    @Test(expectedExceptions = RuntimeException.class, groups = "error-test")
    public void testFlowEngine() {
        int balance = (int) FlowEngine.instance()
                .register("id", "123456")
                .register("userService", userService)
                .register("accountService", accountService)
                .run(FlowEnum.FLOW_NOT_EXIST_COMPLEX_FLOW)
                .retrieve("balance");
        Assert.assertEquals(balance, 12345);
    }
}
