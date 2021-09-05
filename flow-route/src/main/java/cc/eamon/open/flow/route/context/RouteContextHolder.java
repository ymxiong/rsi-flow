package cc.eamon.open.flow.route.context;

import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-07-20 21:09:29
 */
public class RouteContextHolder {

    /**
     * init Context for current thread
     */
    private static ThreadLocal<RouteContext> local = ThreadLocal.withInitial(RouteContext::new);

    private RouteContextHolder() {
    }

    public static RouteContext get() {
        if (local.get() == null) {
            local.set(new RouteContext());
        }
        return local.get();
    }

    public static void clear() {
        local.remove();
    }

    public Object retrieveVariable(String variableName) {
        return get().retrieveVariable(variableName);
    }

    public void registerVariable(String variableName, Object variable) {
        // 注册variable
        get().registerVariable(variableName, variable);
    }

    public void registerVariables(Map<String, Object> variables) {
        // 注册variable
        get().registerVariables(variables);
    }

}
