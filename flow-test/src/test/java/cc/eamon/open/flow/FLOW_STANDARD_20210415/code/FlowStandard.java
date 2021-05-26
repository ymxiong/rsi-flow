package cc.eamon.open.flow.FLOW_STANDARD_20210415.code;

import cc.eamon.open.flow.core.stereotype.Flow;


public class FlowStandard {

    // 去除姓名中的空格
    @Flow(value = "FLOW_STANDARD_20210415_1")
    public String removeBlank(String userName) {
        return userName.replaceAll(" ", "");
    }
}
