package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsyncVoidResult implements RouteResult {

    private List<Target> nextNodes;

    public AsyncVoidResult(List<Target> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public List<Target> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<Target> nextNodes) {
        this.nextNodes = nextNodes;
    }

    @Override
    public int getTargetsSize() {
        return nextNodes.size();
    }
}
