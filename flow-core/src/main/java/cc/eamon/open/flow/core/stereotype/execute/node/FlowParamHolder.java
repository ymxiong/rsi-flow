package cc.eamon.open.flow.core.stereotype.execute.node;

import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 21:52:09
 */
public class FlowParamHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowParamHolder.class);

    /**
     * 流程参数配置
     */
    private FlowParamConfig flowParamConfig;

    private TypeEnums type;

    public FlowParamHolder(FlowParamConfig flowParamConfig) {
        this.flowParamConfig = flowParamConfig;
        type = TypeEnums.getType(flowParamConfig.getType());
    }

    public boolean verify(TypeEnums type) {
        return this.type == type;
    }

    public String getName() {
        return flowParamConfig.getName();
    }

    public TypeEnums getType() {
        return type;
    }

    public String getScope() {
        return flowParamConfig.getScope();
    }


}
