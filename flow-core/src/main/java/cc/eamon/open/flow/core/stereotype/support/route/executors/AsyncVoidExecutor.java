package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowSupplier;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cc.eamon.open.flow.route.Routes;
import cc.eamon.open.flow.route.result.AsyncVoidResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AsyncVoidExecutor implements RouteExecutor {


    @Override
    public void execute(RouteResult routeResult, Executor parentExecutor) {
        // 执行异步
        if (!(routeResult instanceof AsyncVoidResult)) return;
        List<Target> nextNodes = ((AsyncVoidResult) routeResult).getNextNodes();
        Routes routes = ExecutorHolder.get().getRoutes();

        for (Target nextNode : nextNodes) {
            if (nextNode.getId().equals(RouteEnums.END.getId())) return;
            FlowNodeHolder holder = (FlowNodeHolder) nextNode;
            Map<String, Object> paramMap = new HashMap<>();
            if (holder.isAsync()) {
                FlowThread thread = new FlowThread(holder, new FlowContextExtractor(), paramMap, routes);
                CompletableFuture.runAsync(thread);
            }else {
                ExecutorHolder.get().addExecuteNode(holder);
            }

        }
    }
}
