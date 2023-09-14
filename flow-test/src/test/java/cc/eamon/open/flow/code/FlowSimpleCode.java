package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:02:12
 */
public class FlowSimpleCode {

    @Flow(value = "FLOW_SIMPLE_TRIM")
    public void trim(String value) {
        FlowEngine.instance().register("trimValue", value.trim());
    }

    @Flow(value = "FLOW_SIMPLE_REPLACE_BLANK")
    public void replaceBlank(String value) {
        FlowEngine.instance().register("replaceBlankValue", value.replace(" ", ""));
    }

    @Flow(value = "FLOW_SIMPLE_ADD_BLANK")
    public void addBlank(String value) {
        FlowEngine.instance().register("addBlankValue", value + " ");
    }

    @Flow(value = "FLOW_SIMPLE_REPLACE_ALL_BLANK")
    public void replaceAll(String value) {
        FlowEngine.instance().register("replaceAllBlankValue", value.replaceAll(" ", ""));
    }

}
