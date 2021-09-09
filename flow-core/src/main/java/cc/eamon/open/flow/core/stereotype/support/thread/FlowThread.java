package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;


/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class FlowThread implements Runnable {

    private FlowNodeHolder flowNodeHolder;

    private FlowContextExtractor parentContextExtractor;

    private Executor parentExecutor;

    public FlowThread(FlowNodeHolder flowNodeHolder, FlowContextExtractor parentContextExtractor) {
        this.flowNodeHolder = flowNodeHolder;
        this.parentContextExtractor = parentContextExtractor;
        parentExecutor = ExecutorHolder.get();
    }


    @Override
    public void run() {
        // 因为线程池是复用线程，所以要先清空一次
        FlowContextHolder.clear();
        ExecutorHolder.clear();
        if (parentContextExtractor != null) {
            // TODO:优化从父线程拿参数
            FlowContextHolder.get().registerVariable(parentContextExtractor);
            ExecutorHolder.get().setParentExecutor(parentExecutor);
            ExecutorHolder.get().addExecuteNode(flowNodeHolder);
        }
        try {
//            flowNodeHolder.execute();
        } catch (Exception e) {
            throw e;
        }
    }
}
