package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.Dispose;
import cc.eamon.open.flow.core.context.FlowContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThreadPool;

import java.util.concurrent.ExecutionException;
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

//    public FlowEngine run(String flowName) {
//        FlowThreadPool.init();
//        run(flowName,null);
//        return this;
//    }

    public FlowEngine run(String flowName) {
        FlowHolder flowHolder = dispose.getFlowHolders().get(flowName);
        FlowThread flowThread=new FlowThread(flowHolder,FlowContextHolder.get());
//        FutureTask<Object> futureTask = new FutureTask<Object>(flowThread);
        FutureTask<FlowContext> futureTask = new FutureTask<FlowContext>(flowThread);
        Future future= FlowThreadPool.pool.submit(futureTask);
        FlowContext flowContext=null;
        boolean flag=true;
        while(flag){
            if (future.isDone()){
                try {
                    flowContext=(FlowContext) future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                flag=false;
            }
        }
//        flowHolder.execute();
        return this;
    }


    /**
     * 注入参数
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

    /**
     * 注销参数
     * @param paramNames
     * @return
     */
    public FlowEngine release(String... paramNames) {
        FlowContextHolder.get().getVariableMap().remove(paramNames);
        FlowContextHolder.get().getVariableTypeMap().remove(paramNames);
        return this;
    }

    //
    public void release() {
        FlowContextHolder.clear();
    }


    /**
     * 获取参数
     * @param paramName
     * @return
     */
    public Object retrieve(String paramName) {
        return FlowContextHolder.get().getVariableMap().get(paramName);
    }


}
