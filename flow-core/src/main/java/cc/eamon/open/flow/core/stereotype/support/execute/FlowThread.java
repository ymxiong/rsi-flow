package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;

import java.util.concurrent.Callable;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class FlowThread implements Callable<Object> {

    private FlowHolder flowHolder;

    private FlowContext parentContext;

    public FlowThread(FlowHolder flowHolder,FlowContext parentContext) {
        this.flowHolder = flowHolder;
        this.parentContext= parentContext;
    }

    @Override
    public Object call() throws Exception {
        //所有都需要做
        FlowContextHolder.init();
        FlowContextHolder.get().setParentContext(parentContext);
        flowHolder.execute();
        //TODO:只有子线程才需要做
        if (FlowContextHolder.get().getParentContext()!=null) {
            //执行完成注入参数到父Context里面
            FlowContextHolder.get().getParentContext().getVariableTypeMap().putAll(FlowContextHolder.get().getVariableTypeMap());
            FlowContextHolder.get().getParentContext().getVariableMap().putAll(FlowContextHolder.get().getVariableMap());
            //注销
            FlowContextHolder.clear();
        }
        return null;
    }
}
