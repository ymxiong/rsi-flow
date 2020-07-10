package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.Dispose;
import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowThread;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowThreadPool;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    private Dispose dispose = new Dispose();

    public static FlowEngine instance() {
        // 保障同一线程仅有单一执行的流程引擎
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

    public FlowEngine run(String flowName) {
        FlowThreadPool.init();
        run(flowName,null);
        return this;
    }

    public FlowEngine run(String flowName, FlowContext parentFlowContext) {
        FlowHolder flowHolder = dispose.getFlowHolders().get(flowName);
        FlowThread flowThread=new FlowThread(flowHolder,parentFlowContext);
        FutureTask<Object> futureTask = new FutureTask<Object>(flowThread);
        Future future= FlowThreadPool.pool.submit(futureTask);
        //flowHolder.execute();
        return this;
    }

    /**
     * 注入参数
     *
     * @param paramName
     * @param paramValue
     * @return
     */
    public FlowEngine register(String paramName, Object paramValue) {
        //注入参数到上下文中
        FlowContextHolder.get().getVariableMap().put(paramName, paramValue);
        FlowContextHolder.get().getVariableTypeMap().put(paramName, paramValue.getClass());
        return this;
    }

    public FlowEngine release(Object... param) {
//        FlowContextHolder.get().getVariableMap().remove(param);
//        FlowContextHolder.get().getVariableTypeMap().remove(param);
        return this;
    }

    //
    public void release() {
        FlowContextHolder.clear();
    }


    /**
     * 取得参数
     *
     * @param paramName
     * @return
     */
    public Object retrieve(String paramName) {
        return FlowContextHolder.get().getVariableMap().get(paramName);
    }


}
