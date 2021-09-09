package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.Routes;
import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsynAllResult implements RouteResult {

    private List<Target> asynNodes;


    public List<Target> getAsynNodes() {
        return asynNodes;
    }

    public AsynAllResult(List<Target> asynNodes) {
        this.asynNodes = asynNodes;
    }
}
