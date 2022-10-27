package cc.eamon.open.flow.core.context;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.monitor.MonitorBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 00:48:38
 */
public class FlowContext {

    /**
     * 当前线程流程引擎
     */
    private FlowEngine flowEngine;

    /**
     * 当前线程流程名称
     */
    private MonitorBus monitorBus = new MonitorBus();

    /**
     * 上下文分域
     */
    private Map<String, FlowScopeContext> scopeContextMap = new HashMap<>();

    public FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public void shareScope(String targetScope) {
        shareScope(FlowConstants.SCOPE_DEFAULT, targetScope);
    }

    public void shareScope(String sourceScope, String targetScope) {
        scopeContextMap.putIfAbsent(sourceScope, new FlowScopeContext(sourceScope));
        scopeContextMap.put(targetScope, scopeContextMap.get(sourceScope));
    }

    public Map<String, Object> retrieveVariables() {
        return retrieveVariables(FlowConstants.SCOPE_DEFAULT);
    }

    public Map<String, Object> retrieveVariables(String scope) {
        FlowScopeContext scopeContext = scopeContextMap.get(scope);
        if (scopeContext == null) return new HashMap<>();
        return scopeContext.getVariableMap();
    }

    public Object retrieveVariable(String variableName) {
        return retrieveVariable(FlowConstants.SCOPE_DEFAULT, variableName);
    }

    public Object retrieveVariable(String scope, String variableName) {
        FlowScopeContext scopeContext = scopeContextMap.get(scope);
        if (scopeContext == null) return null;
        Object variable = scopeContext.retrieveVariable(variableName);
        if (variable == null) {
            FlowScopeContext defaultScopeContext = scopeContextMap.get(FlowConstants.SCOPE_DEFAULT);
            variable = scopeContext.retrieveVariable(variableName);
        }
        return variable;
    }

    public void registerVariable(String scope, String variableName, Object variable) {
        // 注册variable
        scopeContextMap.putIfAbsent(scope, new FlowScopeContext(scope));
        FlowScopeContext context = scopeContextMap.get(scope);
        context.registerVariable(variableName, variable);
    }


    public void registerVariable(String variableName, Object variable) {
        // 注册variable
        scopeContextMap.putIfAbsent(FlowConstants.SCOPE_DEFAULT, new FlowScopeContext(FlowConstants.SCOPE_DEFAULT));
        FlowScopeContext context = scopeContextMap.get(FlowConstants.SCOPE_DEFAULT);
        context.registerVariable(variableName, variable);
    }

    public void releaseVariables(String scope, String... variableName) {
        FlowScopeContext scopeContext = scopeContextMap.get(scope);
        if (scopeContext == null) return;
        scopeContext.releaseVariables(variableName);
    }

    public Map<String, Object> getScopeParams(String scope) {
        FlowScopeContext scopeContext = scopeContextMap.get(scope);
        if (scopeContext != null) {
            return scopeContext.getVariableMap();
        }
        return null;
    }

    public MonitorBus getMonitorBus() {
        return monitorBus;
    }

    public void registerVariable(FlowContextExtractor flowContextExtractor) {
        this.scopeContextMap.putAll(flowContextExtractor.getScopeContextMap());
    }

    public Map<String, FlowScopeContext> getScopeContextMap() {
        return scopeContextMap;
    }
}
