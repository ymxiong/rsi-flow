package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsyncAllResult implements RouteResult {

    private List<Target> asyncNodes;


    public List<Target> getAsyncNodes() {
        return asyncNodes;
    }

    public AsyncAllResult(List<Target> asynNodes) {
        this.asyncNodes = asynNodes;
    }

    @Override
    public int getTargetsSize() {
        return asyncNodes.size();
    }
}
