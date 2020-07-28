package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.context.FlowDispose;
import cc.eamon.open.flow.core.stereotype.support.execute.FlowHolder;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    private FlowDispose flowDispose = new FlowDispose();

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
        FlowHolder flowHolder = flowDispose.getFlowHolders().get(flowName);
        flowHolder.execute();
//        //TODO:多线程好像不应该放在这里，此处入口
//        FlowContextExtractor flowContextExtractor = new FlowContextExtractor();
//        FlowThread flowThread = new FlowThread(flowHolder, flowContextExtractor);
//        FutureTask<FlowContextExtractor> futureTask = new FutureTask<FlowContextExtractor>(flowThread);
//        Future future = FlowThreadPool.pool.submit(futureTask);
//        FlowContextExtractor childContext = null;
//        boolean flag = true;
//        while (flag) {
//            if (future.isDone()) {
//                try {
//                    childContext = (FlowContextExtractor) future.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//                flag = false;
//            }
//        }
//        //注入需要的参数到当前线程
//        registerChildVariable(childContext);
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
        FlowContextHolder.get().registerVariable(paramName, paramValue, paramValue.getClass());
        return this;
    }

    /**
     * 注销参数
     *
     * @param paramNames
     * @return
     */
    public FlowEngine release(String... paramNames) {
        FlowContextHolder.get().releaseVariable(paramNames);
        return this;
    }

    //
    public void release() {
        FlowContextHolder.clear();
    }


    /**
     * 获取参数
     *
     * @param paramName
     * @return
     */
    public Object retrieve(String paramName) {
        return FlowContextHolder.get().retrieveVariable(paramName);
    }


}
