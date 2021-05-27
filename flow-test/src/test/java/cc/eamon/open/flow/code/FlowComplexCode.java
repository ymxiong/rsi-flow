package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 15:19:33
 */
public class FlowComplexCode {

    private FlowSimpleCode flowSimpleCode = new FlowSimpleCode();

    @Flow(value = "FLOW_COMPLEX_REPLACE", retrieve = "trimValue")
    public String process(String value) {
        return flowSimpleCode.replaceAll(flowSimpleCode.trim(value));
    }


}
