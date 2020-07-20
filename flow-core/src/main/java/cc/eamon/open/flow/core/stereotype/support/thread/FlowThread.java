package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.FlowExecutable;

import java.util.concurrent.Callable;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class FlowThread implements Callable<FlowContext> {

    private FlowExecutable flowExecutable;

    private FlowContext parentContext;


    public FlowThread(FlowExecutable flowExecutable, FlowContext parentContext) {
        this.flowExecutable = flowExecutable;
        this.parentContext = parentContext;
    }


    @Override
    public FlowContext call() throws Exception {
        //因为线程池是复用线程，所以要先清空一次
        FlowContextHolder.clear();
        FlowContextHolder.init();
        if (parentContext != null) {
            FlowContextHolder.get().getVariableMap().putAll(parentContext.getVariableMap());
            FlowContextHolder.get().getVariableTypeMap().putAll(parentContext.getVariableTypeMap());
        }
        flowExecutable.execute();
        if (parentContext != null) {
            parentContext.getVariableMap().putAll(FlowContextHolder.get().getVariableMap());
            parentContext.getVariableTypeMap().putAll(FlowContextHolder.get().getVariableTypeMap());
        }
        return FlowContextHolder.get();
    }


}
