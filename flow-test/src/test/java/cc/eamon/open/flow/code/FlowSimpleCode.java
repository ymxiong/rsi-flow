package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:02:12
 */
public class FlowSimpleCode {

    @Flow(value = "FLOW_SIMPLE_TRIM", retrieve = "trimValue")
    public String trim(String value) {
        return value.trim();
    }

    @Flow(value = "FLOW_SIMPLE_REPLACE_BLANK", retrieve = "replaceBlankValue")
    public String replaceBlank(String value) {
        return value.replace(" ", "");
    }

    @Flow(value = "FLOW_SIMPLE_ADD_BLANK", retrieve = "addBlankValue")
    public String addBlank(String value) {
        return value + " ";
    }

    @Flow(value = "FLOW_SIMPLE_REPLACE_ALL_BLANK", retrieve = "replaceAllBlankValue")
    public String replaceAll(String value) {
        return value.replaceAll(" ", "");
    }

}
