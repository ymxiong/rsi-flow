package cc.eamon.open.flow.FLOW_MODEL;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.model.People;
import cc.eamon.open.flow.model.TestModel;
import org.junit.Test;
import org.testng.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowModelTest {

    @Test()
    public void modelPackageInit() throws Exception {
        FlowEngine flowEngine = FlowEngine.instance();
        TestModel testModel = new TestModel("zz", 20, 1000.0);
        Map<String, Object> map = testModel.buildMap();
        System.out.println(flowEngine.register(FlowConstants.SCOPE_DEFAULT, "people", new People("1", 18, new HashMap<>(), new Date(), new Date()))
                .register(FlowConstants.SCOPE_DEFAULT, "id", "1")
                .register(FlowConstants.SCOPE_DEFAULT, "age", 18)
                .run("FLOW_MODEL")
                .retrieveFlowResults("FLOW_MODEL"));
        flowEngine.release();
//        Object check = ModelEngine.build("testModel", "cc.eamon.open.flow.model.TestModel", map, FlowContextHolder.get().retrieveVariables());
//        Assert.assertTrue(check.getClass().equals(testModel.getClass()));
    }


    @Test()
    public void modelJSONInit() throws Exception {
        FlowEngine.instance();
        TestModel testModel = new TestModel("zz", 20, 1000.0);
        Map<String, Object> map = testModel.buildMap();
        Object check = ModelEngine.build("testModel", "TestModel", map, FlowContextHolder.get().retrieveVariables());
        Assert.assertTrue(check.getClass().equals(testModel.getClass()));
    }

}
