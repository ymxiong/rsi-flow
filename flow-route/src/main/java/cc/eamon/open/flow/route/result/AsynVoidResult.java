package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

public class AsynVoidResult implements RouteResult {

    private Target asynNode;


    private Target normalNode;

    public Target getAsynNode() {
        return asynNode;
    }

    public Target getNormalNode() {
        return normalNode;
    }

    public AsynVoidResult(Target asynNode, Target normalNode) {
        this.asynNode = asynNode;
        this.normalNode = normalNode;
    }
}
