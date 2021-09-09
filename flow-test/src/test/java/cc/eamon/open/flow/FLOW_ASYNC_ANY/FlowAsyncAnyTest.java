package cc.eamon.open.flow.FLOW_ASYNC_ANY;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowAsyncAnyTest {

    @Test()
    public void caseAsyncAny() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "birthday", "19960723")
                .run("FLOW_ASYNC_ANY")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "message1");
        Assert.assertEquals(name, "1");
        FlowEngine.instance().release();
    }


}
