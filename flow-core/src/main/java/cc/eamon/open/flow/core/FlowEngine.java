package cc.eamon.open.flow.core;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.FlowInitializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    private static Logger logger = LoggerFactory.getLogger(FlowEngine.class);

    private FlowInitializer flowInitializer = new FlowInitializer();

    public static FlowEngine instance() {
        //  保障同一线程仅有单一执行的流程引擎
        if (FlowContextHolder.get() != null && FlowContextHolder.get().getFlowEngine() == null) {
            synchronized (FlowEngine.class) {
                if (FlowContextHolder.get().getFlowEngine() == null) {

                    FlowContextHolder.get().setFlowEngine(new FlowEngine());
                }
            }
        }
        return FlowContextHolder.get().getFlowEngine();
    }

    private FlowEngine() {
    }


    public FlowEngine run(String flowId) throws Exception {
        FlowHolder flowHolder = flowInitializer.getFlowHolder(flowId);
        if (flowHolder == null) throw new FlowNotFoundException();
        flowHolder.execute();
        return this;
    }


    /**
     * 注入参数
     *
     * @param paramName  注册参数名
     * @param paramValue 注册参数对象
     * @return 流程引擎实例
     */
    public FlowEngine register(String paramName, Object paramValue) {
        return register(FlowConstants.SCOPE_DEFAULT, paramName, paramValue);
    }

    /**
     * 注入参数
     *
     * @param paramName  注册参数名
     * @param paramValue 注册参数对象
     * @return 流程引擎实例
     */
    public FlowEngine register(String scope, String paramName, Object paramValue) {
        FlowContextHolder.get().registerVariable(scope, paramName, paramValue);
        return this;
    }

    /**
     * 注销参数
     *
     * @param paramNames 参数名集合
     * @return 流程引擎实例
     */
    public FlowEngine release(String... paramNames) {
        return release(FlowConstants.SCOPE_DEFAULT, paramNames);
    }

    /**
     * 注销某个域下的参数
     *
     * @param paramNames 参数名集合
     * @return 流程引擎实例
     */
    public FlowEngine release(String scope, String... paramNames) {
        FlowContextHolder.get().releaseVariables(scope, paramNames);
        return this;
    }


    /**
     * 释放流程引擎
     */
    public void release() {
        FlowContextHolder.clear();
    }


    /**
     * 从默认域获取对象
     *
     * @param paramName 参数名
     * @return 对象
     */
    public Object retrieve(String paramName) {
        return this.retrieve(FlowConstants.SCOPE_DEFAULT, paramName);
    }

    /**
     * 从指定域获取对象
     *
     * @param paramName 参数名
     * @return 对象
     */
    public Object retrieve(String scope, String paramName) {
        return FlowContextHolder.get().retrieveVariable(scope, paramName);
    }


}
