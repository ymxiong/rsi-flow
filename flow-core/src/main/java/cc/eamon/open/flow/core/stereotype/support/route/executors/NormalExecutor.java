package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.core.stereotype.support.route.ExecutorHolder;
import cc.eamon.open.flow.route.result.NormalResult;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.stereotype.Target;

public class NormalExecutor implements RouteExecutor {

    @Override
    public void execute(RouteResult routeResult, Executor parentExecutor){
        if (!(routeResult instanceof NormalResult)) return;
        Target nextNode = ((NormalResult) routeResult).getNextNode();
        if (nextNode.getId().equals(RouteEnums.END.getId())) return;
        FlowNodeHolder holder = (FlowNodeHolder) nextNode;
        ExecutorHolder.get().addExecuteNode(holder);
    }
}
