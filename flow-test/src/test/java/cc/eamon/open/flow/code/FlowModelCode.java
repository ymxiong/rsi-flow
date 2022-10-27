package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.model.People;
import cc.eamon.open.flow.model.TestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:02:12
 */
public class FlowModelCode {

    @Flow(value = "FLOW_MODEL_CODE")
    public void flowModelCode(String id, Integer age, People people, List<TestModel> testModelList) {
        System.out.println(testModelList);
        Map<String, Object> student = new HashMap<>();
        student.put("id", id);
        student.put("age", age);
        student.put("people", people);
        FlowEngine.instance().register("student", student);
    }

}
