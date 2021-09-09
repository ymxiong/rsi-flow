package cc.eamon.open.flow.core.stereotype.support.route;


import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowNodeHolder;
import cc.eamon.open.flow.core.stereotype.execute.holder.FlowResultHolder;
import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.core.stereotype.support.route.executors.RouteExecutorEnums;
import cc.eamon.open.flow.route.Routes;
import cc.eamon.open.flow.route.enums.RouteEnums;
import cc.eamon.open.flow.route.result.RouteResult;
import cc.eamon.open.flow.route.stereotype.Source;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Executor {

    /**
     * 流程路由容器
     */
    private Routes routes;


    /**
     * 流程路由容器
     */
    private Routes parentRoutes;


    /**
     * 流程路由容器
     */
    private Executor parentExecutor;

    private Queue<Source> executeQueue = new LinkedBlockingQueue<>();


    public Executor() {
    }

    public void setRoutes(Routes routes) {
        if (this.routes != null) {
            parentRoutes = this.routes;
        }
        this.routes = routes;
    }

    public void recoverRoutes() {
        if (this.parentRoutes != null) {
            routes = this.parentRoutes;
            this.parentRoutes = null;
        }

    }


    public Routes getRoutes() {
        return routes;
    }

    public void setParentExecutor(Executor parentExecutor) {
        this.parentExecutor = parentExecutor;
    }

    public Executor getParentExecutor() {
        return parentExecutor;
    }

    public void execute(Map<String, Object> variables) {
        while (this.executeQueue.size() > 0) {
            Source source = this.executeQueue.poll();
            if (source == null) break;
            // 获取路由后的结果
            if (!source.getId().equals(RouteEnums.START.getId())) {
                FlowNodeHolder holder = (FlowNodeHolder) source;
                holder.execute();
                for (FlowResultHolder result : holder.getResultHolders()) {
                    Object variable = FlowContextHolder.get().retrieveVariable(result.getScope(), result.getName());
                    variables.put(result.getName(), variable);
                }
            }

            RouteResult routeResult = routes.executeByStrategy(source, variables);
            RouteExecutor routeExecutor = RouteExecutorEnums.getExecutor(routeResult).getRouteExecutor();
            try {
                routeExecutor.execute(routeResult, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void addExecuteNode(Source source) {
        if (source.equals(executeQueue.peek()))
            return;
        executeQueue.add(source);
    }


}
