package cc.eamon.open.flow.core.stereotype.execute.holder;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowException;
import cc.eamon.open.flow.core.error.FlowNodeRuntimeException;
import cc.eamon.open.flow.core.error.ParamNotMatchException;
import cc.eamon.open.flow.core.error.FlowNodeResultNotExistException;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.ExecutableEnums;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import cc.eamon.open.flow.route.stereotype.Source;
import cc.eamon.open.flow.route.stereotype.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lucas
 * Date: 2020/7/12
 * Email: lucas@eamon.cc
 */
public class FlowNodeHolder implements Source, Target {

    private static Logger logger = LoggerFactory.getLogger(FlowNodeHolder.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;

    /**
     * 流程节点参数容器
     */
    private List<FlowParamHolder> paramHolders = new ArrayList<>();

    /**
     * 流程节点结果容器
     */
    private List<FlowResultHolder> resultHolders = new ArrayList<>();

    /**
     * 流程代码`
     */
    private Executable flowExecutable;


    /**
     * 流程代码
     */
    private boolean isAsync = false;

    FlowNodeHolder(FlowNodeConfig flowNodeConfig) throws Exception {
        this.flowNodeConfig = flowNodeConfig;
        Class<? extends Executable> executable = ExecutableEnums.getType(flowNodeConfig.getType()).getExecutable();
        try {
            flowExecutable = executable.getDeclaredConstructor(FlowNodeConfig.class).newInstance(flowNodeConfig);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.toString());
            throw new FlowNodeRuntimeException(flowNodeConfig.getId(), e.getMessage());
        } catch (FlowException e) {
            logger.error(e.toString());
            throw e;
        }
        for (FlowParamConfig param : flowNodeConfig.getParams()) {
            paramHolders.add(new FlowParamHolder(param));
        }
        for (FlowResultConfig result : flowNodeConfig.getResults()) {
            resultHolders.add(new FlowResultHolder(result));
        }
        if (flowNodeConfig.getAsync() == null) {
            this.isAsync = false;
        } else {
            this.isAsync = flowNodeConfig.getAsync();
        }


    }

    public void execute() throws Exception {
        // 校验参数
        verifyParams();
        // 执行流程
        flowExecutable.execute();
        // 校验结果
        verifyResults();
    }

    private void verifyParams() {
        List<TypeEnums> paramTypes = flowExecutable.getParamsTypes();
        if (paramTypes.size() != paramHolders.size()) {
            System.out.println("p size:" + paramTypes.size() + " h size:" + paramHolders.size());
            throw new ParamNotMatchException(flowNodeConfig.getId(), "");
        }
        for (int i = 0; i < paramHolders.size(); i++) {
            paramHolders.get(i).verify(paramTypes.get(i));
        }
    }

    private void verifyResults() {
        for (FlowResultHolder resultHolder : resultHolders) {

            Object variable = FlowContextHolder.get().retrieveVariable(resultHolder.getScope(), resultHolder.getName());
            if (variable == null && resultHolder.getNotNull())
                throw new FlowNodeResultNotExistException(flowNodeConfig.getId(), resultHolder.getScope(), resultHolder.getName());
        }
    }

    public String getId() {
        return flowNodeConfig.getId();
    }

    public String getType() {
        return flowNodeConfig.getType();
    }


    public List<FlowParamHolder> getParamHolders() {
        return paramHolders;
    }

    public List<FlowResultHolder> getResultHolders() {
        return resultHolders;
    }

    public boolean isAsync() {
        return isAsync;
    }
}
