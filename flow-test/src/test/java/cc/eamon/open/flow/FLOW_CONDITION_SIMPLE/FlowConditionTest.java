package cc.eamon.open.flow.FLOW_CONDITION_SIMPLE;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowConditionTest {

    @Test()
    public void caseConditionAdult() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "birthday", "19960723")
                .run("FLOW_CONDITION_SIMPLE")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "message");
        Assert.assertEquals(name, "您是成年人");
        FlowEngine.instance().release();
    }


    @Test()
    public void caseConditionChild() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "birthday", "20260723")
                .run("FLOW_CONDITION_SIMPLE")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "message");
        Assert.assertEquals(name, "您是小孩");
        FlowEngine.instance().release();
    }

}
