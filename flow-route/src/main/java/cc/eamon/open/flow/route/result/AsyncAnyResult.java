package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsyncAnyResult implements RouteResult {

    private List<Target> asyncNodes;

    public List<Target> getAsyncNodes() {
        return asyncNodes;
    }

    public AsyncAnyResult(List<Target> asynNodes) {
        this.asyncNodes = asynNodes;
    }

    @Override
    public int getTargetsSize() {
        return asyncNodes.size();
    }
}
