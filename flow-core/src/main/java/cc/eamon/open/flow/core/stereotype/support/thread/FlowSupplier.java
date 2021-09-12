package cc.eamon.open.flow.core.stereotype.support.thread;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.Routes;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class FlowSupplier implements Supplier<FlowContextExtractor> {


    private FlowNodeHolder flowNodeHolder;


    private FlowContextExtractor flowContextExtractor;


    private Executor parentExecutor;


    private Map<String, Object> paramMap;

    private Routes routes;

    public FlowSupplier(FlowNodeHolder flowNodeHolder, FlowContextExtractor flowContextExtractor, Map<String, Object> paramMap, Routes routes) {
        this.flowNodeHolder = flowNodeHolder;
        this.flowContextExtractor = flowContextExtractor;
        this.parentExecutor = ExecutorHolder.get();
        this.paramMap = paramMap;
        this.routes = routes;
    }

    @Override
    public FlowContextExtractor get() {
        FlowContextHolder.clear();
        ExecutorHolder.clear();
        if (flowContextExtractor != null) {
            // 注册参数
            FlowContextHolder.get().registerVariable(flowContextExtractor);
//            flowNodeHolder.execute();
            // 运行
            ExecutorHolder.get().setRoutes(routes);
            ExecutorHolder.get().setParentExecutor(parentExecutor);
            ExecutorHolder.get().addExecuteNode(flowNodeHolder);
            ExecutorHolder.get().execute(this.paramMap);
        }
        return new FlowContextExtractor();
    }
}
