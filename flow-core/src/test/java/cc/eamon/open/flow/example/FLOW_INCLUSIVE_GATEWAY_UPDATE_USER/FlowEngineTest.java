package cc.eamon.open.flow.example.FLOW_INCLUSIVE_GATEWAY_UPDATE_USER;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.User;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowEngineTest {
    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    //多路网关测试【同时更改性别和年龄】
    @Test
    public void testFlowEngine() {
        User user = (User) FlowEngine.instance()
                .register("userName", "小朱")
                .register("newGender", "女")
                .register("newAge", 25)
                .register("userService", userService)
                .register("accountService", accountService)
                .run(FlowEnum.FLOW_INCLUSIVE_GATEWAY_UPDATE_USER)
                // 取出执行结果
                .retrieve("user");

        Assert.assertEquals(user.getGender(), "女");
        Assert.assertEquals(user.getAge(), 25);
    }
}
