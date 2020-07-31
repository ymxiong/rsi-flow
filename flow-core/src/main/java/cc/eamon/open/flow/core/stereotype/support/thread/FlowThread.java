package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class FlowThread implements Callable<FlowContextExtractor> {

    private FlowExecutable flowExecutable;

    private volatile CountDownLatch countDownLatch;

    private FlowContextExtractor parentContextExtractor;

    public FlowThread(FlowExecutable flowExecutable, FlowContextExtractor parentContextExtractor,CountDownLatch countDownLatch) {
        this.flowExecutable = flowExecutable;
        this.parentContextExtractor = parentContextExtractor;
        this.countDownLatch=countDownLatch;
    }


    @Override
    public FlowContextExtractor call() throws Exception {
        //因为线程池是复用线程，所以要先清空一次
        FlowContextHolder.clear();
        FlowContextHolder.init();
        if (parentContextExtractor != null) {
            //TODO:优化从父线程拿参数
            FlowContextHolder.get().registerVariableList(parentContextExtractor.getVariableMap(),parentContextExtractor.getVariableTypeMap());
        }
        flowExecutable.execute();
        //TODO:执行完后需要把最后一个节点注入到一个List中供给后续路由
        FlowContextExtractor flowContextExtractor=null;
        if (parentContextExtractor != null) {
            //TODO:优化注入参数到父线程中
            flowContextExtractor=new FlowContextExtractor();
        }
        //
        countDownLatch.countDown();
        return flowContextExtractor;
    }


}
