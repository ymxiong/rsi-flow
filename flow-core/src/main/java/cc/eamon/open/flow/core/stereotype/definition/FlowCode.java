package cc.eamon.open.flow.core.stereotype.definition;

import cc.eamon.open.flow.config.RouteConfig;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 18:30:21
 */
public interface FlowCode {

    FlowCode getFlowCode();

    String getFlowName();

    void execute(RouteConfig routeConfig, Object... args) throws Exception;

    // TODO: NEED OPTIMIZE
    Method getMethod();

}
