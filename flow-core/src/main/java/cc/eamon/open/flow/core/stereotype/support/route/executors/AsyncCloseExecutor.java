package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.AsynCloseResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Target;

public class AsyncCloseExecutor implements RouteExecutor {

    @Override
    public void execute(RouteResult routeResult, Executor parentExecutor) {
        //把该节点加入到父Executor中
        if (!(routeResult instanceof AsynCloseResult)) return;
        Target nextNode = ((AsynCloseResult) routeResult).getCloseTarget();
        if (nextNode.getId().equals(RouteEnums.END.getId())) return;
        FlowNodeHolder holder = (FlowNodeHolder) nextNode;
        ExecutorHolder.get().getParentExecutor().addExecuteNode(holder);
    }

}
