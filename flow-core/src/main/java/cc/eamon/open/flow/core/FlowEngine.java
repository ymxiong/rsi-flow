package cc.eamon.open.flow.core;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.FlowNotFoundException;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    private static Logger logger = LoggerFactory.getLogger(FlowEngine.class);

    public static FlowEngine instance() {
        //  保障同一线程仅有单一执行的流程引擎
        if (FlowContextHolder.get() != null && FlowContextHolder.get().getFlowEngine() == null) {
            synchronized (FlowEngine.class) {
                if (FlowContextHolder.get().getFlowEngine() == null) {
                    Initializer.init();
                    FlowContextHolder.get().setFlowEngine(new FlowEngine());
                    logger.info("初始化流程引擎完成");
                }
            }
        }
        return FlowContextHolder.get().getFlowEngine();
    }

    private FlowEngine() {
    }


    /**
     * 流程引擎调度入口
     *
     * @param flowId 流程id
     * @return 流程引擎
     * @throws Exception 流程错误
     */
    public FlowEngine run(String flowId) throws Exception {
        // 获取flow容器
        FlowHolder flowHolder = Initializer.getFlowHolder(flowId);
        // 检查容器是否存在
        if (flowHolder == null) throw new FlowNotFoundException(flowId);
        // 共享默认上下文
        FlowContextHolder.get().shareScope(flowId);
        // 执行容器
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
