package cc.eamon.open.flow.core.stereotype.support.route.executors;

import cc.eamon.open.flow.core.stereotype.route.RouteExecutor;
import cc.eamon.open.flow.route.result.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:23:08
 */
public enum RouteExecutorEnums {

    ASYNCALLEXECUTOR(AsyncAllResult.class, new AsyncAllExecutor()),

    ASYNCANYEXECUTOR(AsyncAnyResult.class, new AsyncAnyExecutor()),

    ASYNCVOIDEXECUTOR(AsyncVoidResult.class, new AsyncVoidExecutor()),

    ASYNCCLOSERESULT(AsyncCloseResult.class, new AsyncCloseExecutor()),

    NORMALEXECUTOR(NormalResult.class,new NormalExecutor());

    private Class<? extends RouteResult> routeResult;

    private RouteExecutor routeExecutor;

    RouteExecutorEnums(Class<? extends RouteResult> routeResult, RouteExecutor routeExecutor) {
        this.routeResult = routeResult;
        this.routeExecutor = routeExecutor;
    }

    public Class<? extends RouteResult> getRouteResult() {
        return routeResult;
    }

    public RouteExecutor getRouteExecutor() {
        return routeExecutor;
    }

    public static RouteExecutorEnums getExecutor(RouteResult routeResult)  {
        for (RouteExecutorEnums value : RouteExecutorEnums.values()) {
            if (value.getRouteResult().equals(routeResult.getClass())) return value;
        }
        return null;
    }

}
