package cc.eamon.open.flow.core.stereotype.route;

import cc.eamon.open.flow.core.stereotype.support.route.Executor;
import cc.eamon.open.flow.route.result.RouteResult;

public interface RouteExecutor {
    void execute(RouteResult routeResult, Executor parentExecutor);
}
