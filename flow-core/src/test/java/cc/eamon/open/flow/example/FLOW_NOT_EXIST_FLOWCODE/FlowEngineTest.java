package cc.eamon.open.flow.example.FLOW_NOT_EXIST_FLOWCODE;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    @Test(expectedExceptions = RuntimeException.class, groups = "error-test")
    public void testFlowEngine() {

        // flowcode不存在 [线性编排]
        Account account = (Account) FlowEngine.instance()
                // 注册流程所需参数
                .register("userName", "小朱")
                // 注册流程所需核心服务
                .register("userService", userService)
                .register("accountService", accountService)
                // 执行流程
                .run(FlowEnum.FLOW_NOT_EXIST_FLOW_CODE)
                // 取出执行结果
                .retrieve("account");
    }
}
