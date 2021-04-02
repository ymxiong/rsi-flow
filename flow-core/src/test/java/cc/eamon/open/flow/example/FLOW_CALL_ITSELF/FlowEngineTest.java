package cc.eamon.open.flow.example.FLOW_CALL_ITSELF;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.core.error.FlowCallitSelfException;
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
    @Test(expectedExceptions = FlowCallitSelfException.class, groups = "error-test")
    public void testFlowEngine() throws Exception {
        FlowDispose.test();
        User user = (User) FlowEngine.instance()
                .register(FlowConstants.SCOPE_TEST, "userName", "小朱")
                .register(FlowConstants.SCOPE_TEST, "newAge", 21)
                .register(FlowConstants.SCOPE_TEST, "userService", userService)
                .register(FlowConstants.SCOPE_TEST, "accountService", accountService)
                .run(FlowEnum.FLOW_CALL_ITSELF)
                // 取出执行结果
                .retrieve(FlowConstants.SCOPE_TEST, "user");

        Assert.assertEquals(user.getAge(), 21);
        FlowEngine.instance().release();
    }

}
