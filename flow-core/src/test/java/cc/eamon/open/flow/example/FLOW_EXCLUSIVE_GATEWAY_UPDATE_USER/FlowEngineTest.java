package cc.eamon.open.flow.example.FLOW_EXCLUSIVE_GATEWAY_UPDATE_USER;

import cc.eamon.open.flow.config.constants.FlowConstants;
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

    //唯一网关测试【有权限就更改性别，没有权限不能更改】
    @Test(groups = "test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        User user = (User) FlowEngine.instance()
                .register(FlowConstants.SCOPE_TEST,"userName", "小朱")
                .register(FlowConstants.SCOPE_TEST,"newGender", "女")
                .register(FlowConstants.SCOPE_TEST,"userService", userService)
                .register(FlowConstants.SCOPE_TEST,"accountService", accountService)
                .run(FlowEnum.FLOW_EXCLUSIVE_GATEWAY_UPDATE_USER)
                // 取出执行结果
                .retrieve(FlowConstants.SCOPE_TEST,"user");

        Assert.assertEquals(user.getGender(), "女");
        FlowEngine.instance().release();
    }
}
