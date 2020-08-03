package cc.eamon.open.flow.example.FLOW_NOT_EXIST_NEST_FLOW;

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

    //嵌套调用FlowHolder不存在【更改年龄】
    @Test
    public void testFlowEngine() {
        User user = (User) FlowEngine.instance()
                .register("userName", "小朱")
                .register("newAge", 21)
                .register("userService", userService)
                .register("accountService", accountService)
                .run(FlowEnum.FLOW_NOT_EXIST_NEST_FLOW)
                // 取出执行结果
                .retrieve("user");

        Assert.assertEquals(user.getAge(), 21);
    }
}
