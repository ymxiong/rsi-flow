package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.error.FlowNodeRuntimeException;
import cc.eamon.open.flow.core.stereotype.execute.support.Executable;
import cc.eamon.open.flow.core.stereotype.execute.support.ExecutableEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Author: lucas
 * Date: 2020/7/12
 * Email: lucas@eamon.cc
 */
public class FlowNodeHolder {

    private static Logger logger = LoggerFactory.getLogger(FlowNodeHolder.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;

    /**
     * 流程代码
     */
    private Executable flowExecutable;


    public FlowNodeHolder(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
        Class<? extends Executable> executable = ExecutableEnums.getType(flowNodeConfig.getType()).getExecutable();
        try {
            flowExecutable = executable.getDeclaredConstructor(FlowNodeConfig.class).newInstance(flowNodeConfig);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.toString());
            throw new FlowNodeRuntimeException(flowNodeConfig.getId());
        }
    }

    public void execute() {
        flowExecutable.execute();
    }

    public String getId() {
        return flowNodeConfig.getId();
    }

    public String getType() {
        return flowNodeConfig.getType();
    }

    public String getName() {
        return flowNodeConfig.getName();
    }

}
