package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsynAnyResult implements RouteResult {

    private List<Target> asynNodes;

    public List<Target> getAsynNodes() {
        return asynNodes;
    }

    public AsynAnyResult(List<Target> asynNodes) {
        this.asynNodes = asynNodes;
    }
}
