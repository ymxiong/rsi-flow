package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.processor.FlowProcessor;
import cc.eamon.open.flow.core.context.DisposeContext;
import cc.eamon.open.flow.core.context.DisposeContextBuilder;
import cc.eamon.open.flow.core.route.RouteEngine;
import cc.eamon.open.flow.core.route.entity.ProcessNode;
import cc.eamon.open.flow.example.FlowQueryAccount;
import cc.eamon.open.flow.example.FlowQueryUser;
import cc.eamon.open.flow.remote.entity.Account;
import cc.eamon.open.flow.remote.service.AccountService;
import cc.eamon.open.flow.remote.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
        FlowEngine.init();

        //解析JSON文件封装进DisposeContext中
        try {
            DisposeContextBuilder.parseJson("src/test/resources/flow-example.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据DisposeContext中信息结合@Flow注解解析目标业务代码
        FlowProcessor.annotationProcess(FlowQueryAccount.class);
        FlowProcessor.annotationProcess(FlowQueryUser.class);


        // 调度流程
        Account account = (Account) FlowEngine.instance()
                // 注册流程所需参数
                .register("userName", "小朱")
                // 注册流程所需核心服务
                .register("userService", userService)
                .register("accountService", accountService)
                // 执行流程
//                .run(FlowEnum.FLOW_QUERY_USER_ACCOUNT)
                .run(FlowContextHolder.get().getStartNode())
                // 取出执行结果
                .retrieve("account");
        FlowEngine.clear();

        Assert.assertEquals(account.getId(), "654321");

    }


}
