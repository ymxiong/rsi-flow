package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;


public class FlowExecutableThread implements Runnable {

    private Executable executable;


    private FlowContextExtractor flowContextExtractor;


    private Executor parentExecutor;


    public FlowExecutableThread(Executable executable, FlowContextExtractor flowContextExtractor) {
        this.flowContextExtractor = flowContextExtractor;
        this.parentExecutor = ExecutorHolder.get();
        this.executable = executable;
    }


    @Override
    public void run() {
        FlowContextHolder.clear();
        ExecutorHolder.clear();
        if (flowContextExtractor != null) {
            // 注册参数
            FlowContextHolder.get().registerVariable(flowContextExtractor);
        }
        ExecutorHolder.get().setParentExecutor(parentExecutor);
        try {
            executable.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
