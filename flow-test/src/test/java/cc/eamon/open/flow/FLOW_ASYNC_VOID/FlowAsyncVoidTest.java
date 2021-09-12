package cc.eamon.open.flow.FLOW_ASYNC_VOID;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import org.testng.annotations.Test;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowAsyncVoidTest {

    @Test()
    public void caseAsyncAll() throws Exception {
        Integer age = (Integer) FlowEngine.instance()
                .register(FlowConstants.SCOPE_DEFAULT, "name", "小兔子")
                .register(FlowConstants.SCOPE_DEFAULT, "age", 0)
                .run("FLOW_ASYNC_VOID")
                .retrieve(FlowConstants.SCOPE_DEFAULT, "age");
        FlowEngine.instance().release();
    }


}
