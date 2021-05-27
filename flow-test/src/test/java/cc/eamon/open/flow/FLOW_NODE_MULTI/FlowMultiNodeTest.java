package cc.eamon.open.flow.FLOW_NODE_MULTI;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:26:47
 */
public class FlowMultiNodeTest {

    @Test()
    public void caseMultiNode() throws Exception {
        FlowEngine flowEngine = FlowEngine.instance();
        flowEngine.register(FlowConstants.SCOPE_DEFAULT, "value", "单个流 程节点")
                .run("FLOW_MULTI_NODE");

        String replaceAllBlankValue = (String) flowEngine.retrieve(FlowConstants.SCOPE_DEFAULT, "replaceAllBlankValue");
        String addBlankValue = (String) flowEngine.retrieve(FlowConstants.SCOPE_DEFAULT, "addBlankValue");

        Assert.assertEquals(replaceAllBlankValue, "单个流程节点");
        Assert.assertEquals(addBlankValue, "单个流 程节点 ");
        FlowEngine.instance().release();
    }

}
