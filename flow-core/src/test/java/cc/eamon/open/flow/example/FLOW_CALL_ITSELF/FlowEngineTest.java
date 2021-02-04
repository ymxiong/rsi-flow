package cc.eamon.open.flow.example.FLOW_CALL_ITSELF;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    //调用自身【嵌套调用更改年龄】
    @Test(expectedExceptions = RuntimeException.class, groups = "error-test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        User user = (User) FlowEngine.instance()
                .register("testScope","userName", "小朱")
                .register("testScope","newAge", 21)
                .register("testScope","userService", userService)
                .register("testScope","accountService", accountService)
                .run(FlowEnum.FLOW_CALL_ITSELF)
                // 取出执行结果
                .retrieve("testScope","user");

        Assert.assertEquals(user.getAge(), 21);
    }

}
