package cc.eamon.open.flow.FLOW_REMOTE_HTTP;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-31 13:19:07
 */
public class FlowRemoteHttpTest {

    @Test()
    public void caseHttpTest() throws Exception {
        FlowEngine flowEngine = FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "id", "B207BE79230C4ABBBAE7BA4AAD8AD4F3");
//                .run("FLOW_REMOTE_HTTP");

        String name = (String) flowEngine.retrieve(FlowConstants.SCOPE_DEFAULT, "name");
        String status = (String) flowEngine.retrieve(FlowConstants.SCOPE_DEFAULT, "status");
//        Assert.assertEquals(name, "倚码模型");
//        Assert.assertEquals(status, "STAGE_RUN_FINISHED");
        FlowEngine.instance().release();
    }

}
