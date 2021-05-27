package cc.eamon.open.flow.FLOW_NEST;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-19 09:59:11
 */
public class FlowNestTest {

    @Test()
    public void caseNest() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "value", "流程 节点")
                .run("FLOW_NEST")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "modifyAddValue");
        System.out.println(name);
        Assert.assertEquals(name, "流程节点 ");
        FlowEngine.instance().release();
    }

}