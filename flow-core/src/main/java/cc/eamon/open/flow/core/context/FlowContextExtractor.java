package cc.eamon.open.flow.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas
 * Date: 2020/7/20
 * Email: lucas@eamon.cc
 */
public class FlowContextExtractor {

    /**
     * 线程参数列表
     */
    private Map<String, Object> variableMap = new HashMap<>();
    /**
     * 线程参数类型
     */
    private Map<String, Class> variableTypeMap = new HashMap<>();

    public FlowContextExtractor() {
        FlowContext flowContext = FlowContextHolder.get();
        this.variableMap.putAll(flowContext.getVariableMap());
        this.variableTypeMap.putAll(flowContext.getVariableTypeMap());
    }

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }


    public Map<String, Class> getVariableTypeMap() {
        return variableTypeMap;
    }

    public Object getVariable(String variableName) {
        return variableMap.get(variableName);
    }

    public Class getVariableType(String variableName) {
        return variableTypeMap.get(variableName);
    }


}
