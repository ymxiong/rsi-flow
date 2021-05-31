package cc.eamon.open.flow.FLOW_NODE_SINGLE;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowSingleNodeTest {

    @Test()
    public void caseSingleNode() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "value", "流程 节点")
                .run("FLOW_SINGLE_NODE")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "addBlankValue");
        Assert.assertEquals(name, "流程 节点 ");
        FlowEngine.instance().release();
    }

}
