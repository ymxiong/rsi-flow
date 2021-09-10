package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.Routes;
import cc.eamon.open.flow.route.result.AsynAllResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowSupplier;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AsyncAllExecutor implements RouteExecutor {

    @Override
    public void execute(RouteResult routeResult, Executor parentExecutor) {
        if (!(routeResult instanceof AsynAllResult)) return;
        List<Target> asynNodes = ((AsynAllResult) routeResult).getAsyncNodes();
        List<CompletableFuture<FlowContextExtractor>> futures = new ArrayList<>();
        Routes routes= ExecutorHolder.get().getRoutes();
        for (Target asynNode : asynNodes) {
            if (asynNode.getId().equals(RouteEnums.END.getId())) continue;
            FlowNodeHolder holder = (FlowNodeHolder) asynNode;

            Map<String, Object> paramMap = new HashMap<>();

            FlowSupplier supplier = new FlowSupplier(holder, new FlowContextExtractor(),paramMap,routes);
            CompletableFuture<FlowContextExtractor> future = CompletableFuture.supplyAsync(supplier);
            futures.add(future);
        }
        CompletableFuture result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        result.join();
        try {
            for (CompletableFuture<FlowContextExtractor> future : futures) {
                FlowContextExtractor flowContextExtractor = future.get();
                FlowContextHolder.get().registerVariable(flowContextExtractor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
