package cc.eamon.open.flow.core.stereotype.support.route;

import cc.eamon.open.flow.route.Routes;

public class ExecutorHolder {

    /**
     * 流程路由容器
     */
//    public static Routes routes;

    private static ThreadLocal<Executor> executeQueue = ThreadLocal.withInitial(() ->new Executor());

    private ExecutorHolder() {
    }

    public static Executor get() {
        if (executeQueue.get() == null) {
            executeQueue.set(new Executor());
        }
        return executeQueue.get();
    }


    public static void setRoutes(Routes routes) {
        Executor executor = executeQueue.get();
        executor.setRoutes(routes);
    }

    public static void recoverRoutes() {
        Executor executor = executeQueue.get();
        executor.recoverRoutes();
    }

    public static void clear() {
        executeQueue.remove();
    }

}
