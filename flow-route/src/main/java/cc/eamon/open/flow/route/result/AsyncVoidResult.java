package cc.eamon.open.flow.route.result;


import cc.eamon.open.flow.route.stereotype.Target;

public class AsyncVoidResult implements RouteResult {

    private Target asyncNode;


    private Target normalNode;

    public Target getAsyncNode() {
        return asyncNode;
    }

    public Target getNormalNode() {
        return normalNode;
    }

    public AsyncVoidResult(Target asyncNode, Target normalNode) {
        this.asyncNode = asyncNode;
        this.normalNode = normalNode;
    }

    @Override
    public int getTargetsSize() {
        return 2;
    }
}
