package cc.eamon.open.flow.FLOW_REMOTE_HTTP;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-31 13:19:07
 */
public class FlowRemoteHttpTest {

    @Test()
    public void caseHttpTest() throws Exception {
        String name = (String) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "id", "1C86955EA8644F599E05B8BD809A3AC3")
                .run("FLOW_REMOTE_HTTP")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "nickname");
        System.out.println(name);
        Assert.assertEquals(name, "eamon");
        FlowEngine.instance().release();
    }

}
