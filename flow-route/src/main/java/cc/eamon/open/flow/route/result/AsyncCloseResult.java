package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

import java.util.List;

public class AsyncCloseResult implements RouteResult {

    private Target closeTarget;

    public Target getCloseTarget() {
        return closeTarget;
    }

    public AsyncCloseResult(Target closeTarget) {
        this.closeTarget = closeTarget;
    }

    @Override
    public int getTargetsSize() {
        return 1;
    }
}
