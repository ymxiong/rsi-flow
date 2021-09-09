package cc.eamon.open.flow.core.context;

import java.util.HashMap;
import java.util.Map;

public class FlowContextExtractor {

    private Map<String, FlowScopeContext> scopeContextMap = new HashMap<>();

    public FlowContextExtractor() {
        scopeContextMap.putAll(FlowContextHolder.get().getScopeContextMap());
    }

    public Map<String, FlowScopeContext> getScopeContextMap() {
        return scopeContextMap;
    }
}
