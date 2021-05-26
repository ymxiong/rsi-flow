package cc.eamon.open.flow.example.FLOW_STANDARD_20210415;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.example.enums.FlowEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-19 09:59:11
 */
public class FlowStandardTest {

    @Test()
    public void caseNewStandard() throws Exception {
        String balance = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "userName", "    小   朱   ")
                .run(FlowEnums.FLOW_STANDARD_20210415)
                .retrieve(FlowConstants.SCOPE_DEFAULT, "userName");
        Assert.assertEquals(balance, "小朱");
        FlowEngine.instance().release();
    }

}