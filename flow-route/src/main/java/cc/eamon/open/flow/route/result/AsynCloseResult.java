package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsynCloseResult implements RouteResult {

    private Target closeTarget;

    public Target getCloseTarget() {
        return closeTarget;
    }

    public AsynCloseResult(Target closeTarget) {
        this.closeTarget = closeTarget;
    }
}
