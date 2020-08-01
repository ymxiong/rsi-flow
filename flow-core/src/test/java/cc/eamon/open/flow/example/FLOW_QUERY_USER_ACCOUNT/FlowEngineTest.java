package cc.eamon.open.flow.example.FLOW_QUERY_USER_ACCOUNT;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.example.enums.FlowEnum;
import cc.eamon.open.flow.example.remote.entity.Account;
import cc.eamon.open.flow.example.remote.service.AccountService;
import cc.eamon.open.flow.example.remote.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:18:18
 */
public class FlowEngineTest {

    private UserService userService = new UserService();

    private AccountService accountService = new AccountService();

    @Test
    public void testFlowEngine(){

        // 线性编排
        Account account = (Account) FlowEngine.instance()
                // 注册流程所需参数
                .register("userName", "小朱")
                // 注册流程所需核心服务
                .register("userService", userService)
                .register("accountService", accountService)
                // 执行流程
                .run(FlowEnum.FLOW_QUERY_USER_ACCOUNT)
                // 取出执行结果
                .retrieve("account");

        Assert.assertEquals(account.getId(), "654321");


    }


}
