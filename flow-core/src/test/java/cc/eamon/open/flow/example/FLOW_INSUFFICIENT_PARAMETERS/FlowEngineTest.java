package cc.eamon.open.flow.example.FLOW_INSUFFICIENT_PARAMETERS;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    @Test(expectedExceptions = RuntimeException.class, groups = "insufficient-error-test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        // 参数不足（线性编排）
        Account account = (Account) FlowEngine.instance()
                // 注册流程所需参数
                //.register("userName", "小朱")
                // 注册流程所需核心服务
                .register("testScope","userService", userService)
                .register("testScope","accountService", accountService)
                // 执行流程
                .run(FlowEnum.FLOW_INSUFFICIENT_PARAMETERS)
                // 取出执行结果
                .retrieve("testScope","account");

        Assert.assertEquals(account.getId(), "654321");
    }
}
