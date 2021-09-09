package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.context.FlowContextExtractor;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.result.AsynVoidResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.core.stereotype.support.thread.FlowThread;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.concurrent.CompletableFuture;

public class AsyncVoidExecutor implements RouteExecutor {


    @Override
    public void execute(RouteResult routeResult, Executor parentExecutor){
        // 执行异步
        if (!(routeResult instanceof AsynVoidResult)) return;
        Target asynNode = ((AsynVoidResult) routeResult).getAsynNode();
        if (asynNode.getId().equals(RouteEnums.END.getId())) return;
        FlowNodeHolder asynNodeholder = (FlowNodeHolder) asynNode;
        FlowThread flowThread = new FlowThread(asynNodeholder, new FlowContextExtractor());
        CompletableFuture future = CompletableFuture.runAsync(flowThread);

        // 执行普通
        Target nextNode = ((AsynVoidResult) routeResult).getNormalNode();
        if (nextNode.getId().equals(RouteEnums.END.getId())) return;
        FlowNodeHolder holder = (FlowNodeHolder) nextNode;
//        holder.execute();
        ExecutorHolder.get().addExecuteNode(holder);
    }
}
