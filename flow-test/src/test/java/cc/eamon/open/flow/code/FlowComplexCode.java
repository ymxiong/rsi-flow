package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:19:33
 */
public class FlowComplexCode {

    @Flow(value = "FLOW_COMPLEX_REPLACE", retrieve = "trimValue")
    public void process(String value) {
        FlowEngine.instance().register("trimValue", value.trim());
    }

}
