package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.Routes;

import java.util.Map;


/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class FlowThread implements Runnable {

    private FlowNodeHolder flowNodeHolder;


    private FlowContextExtractor flowContextExtractor;


    private Executor parentExecutor;


    private Map<String, Object> paramMap;

    private Routes routes;

    public FlowThread(FlowNodeHolder flowNodeHolder, FlowContextExtractor flowContextExtractor, Map<String, Object> paramMap, Routes routes) {
        this.flowNodeHolder = flowNodeHolder;
        this.flowContextExtractor = flowContextExtractor;
        this.parentExecutor = ExecutorHolder.get();
        this.paramMap = paramMap;
        this.routes = routes;
    }


    @Override
    public void run() {
        FlowContextHolder.clear();
        ExecutorHolder.clear();
        if (flowContextExtractor != null) {
            // TODO:优化从父线程拿参数
            // 注册参数
            FlowContextHolder.get().registerVariable(flowContextExtractor);
            ExecutorHolder.get().setRoutes(routes);
            ExecutorHolder.get().setParentExecutor(parentExecutor);
            ExecutorHolder.get().addExecuteNode(flowNodeHolder);
            try {
                ExecutorHolder.get().execute(this.paramMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
