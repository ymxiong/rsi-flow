package cc.eamon.open.flow.FLOW_MODEL;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.model.TestModel;

import org.junit.Test;
import org.testng.Assert;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 14:55:19
 */
public class FlowModelTest {

    @Test()
    public void modelPackageInit() throws Exception {
        FlowEngine.instance();
        TestModel testModel = new TestModel("zz", 20, 1000.0);
        Map<String, Object> map = testModel.buildMap();
        Object check = ModelEngine.check("cc.eamon.open.flow.model.TestModel", map);
        Assert.assertTrue(check.getClass().equals(testModel.getClass()));
    }


    @Test()
    public void modelJSONInit() throws Exception {
        FlowEngine.instance();
        TestModel testModel = new TestModel("zz", 20, 1000.0);
        Map<String, Object> map = testModel.buildMap();
        Object check = ModelEngine.check("TestModel", map);
        Assert.assertTrue(check.getClass().equals(testModel.getClass()));
    }

}
