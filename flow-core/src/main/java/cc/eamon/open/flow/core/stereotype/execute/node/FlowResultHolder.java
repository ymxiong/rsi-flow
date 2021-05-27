package cc.eamon.open.flow.core.stereotype.execute.node;

import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 21:52:29
 */
public class FlowResultHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowResultHolder.class);

    /**
     * 流程结果
     */
    private FlowResultConfig flowResultConfig;

    /**
     * 流程数据类型
     */
    private TypeEnums type;

    public FlowResultHolder(FlowResultConfig flowResultConfig) {
        this.flowResultConfig = flowResultConfig;
        type = TypeEnums.getType(flowResultConfig.getType());
    }

    public boolean verify(TypeEnums type) {
        return this.type == type;
    }

    public String getName() {
        return flowResultConfig.getName();
    }

    public TypeEnums getType() {
        return TypeEnums.getType(flowResultConfig.getType());
    }

    public String getScope() {
        return flowResultConfig.getScope();
    }


}
