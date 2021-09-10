package cc.eamon.open.flow.route.result;

import cc.eamon.open.flow.route.stereotype.Target;

public class NormalResult implements RouteResult {

    private Target nextNode;

    public Target getNextNode() {
        return nextNode;
    }

    public NormalResult(Target nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public int getTargetsSize() {
        return 1;
    }
}
