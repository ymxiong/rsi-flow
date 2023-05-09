package cc.eamon.open.flow.FLOW_NONE;

import cc.eamon.open.flow.core.FlowEngine;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowNoneTest {

    @Test()
    public void testFlowNone() throws Exception {
        FlowEngine.instance()
                .run("FLOW_NONE");
        FlowEngine.instance().release();
    }

}
