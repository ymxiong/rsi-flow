package cc.eamon.open.flow.route.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-07-20 21:09:06
 */
class RouteContext {

    private Map<String, Object> variableMap = new HashMap<>();


    Object retrieveVariable(String variableName) {
        return variableMap.get(variableName);
    }

    void registerVariable(String variableName, Object variable) {
        // 注册variable
        variableMap.put(variableName, variable);
    }

    void registerVariables(Map<String, Object> variables) {
        // 注册variable
        variableMap.putAll(variables);
    }

}
