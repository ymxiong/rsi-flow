package cc.eamon.open.flow.FLOW_STANDARD_20210415;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
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
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "value", "    标准 0415   ")
                .run("FLOW_STANDARD_20210415")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "trimValue");
        Assert.assertEquals(name, "标准 0415");
        FlowEngine.instance().release();
    }

}