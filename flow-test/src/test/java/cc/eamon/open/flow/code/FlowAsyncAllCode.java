package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncAllCode {

    @Flow(value = "FLOW_ASYNC_START",retrieve = "start")
    public String FLOW_ASYNC_START() {
        System.out.println("ASYNC_START");
        return "start";
    }

    @Flow(value = "FLOW_ASYNC_NODE_1", retrieve = "message1")
    public String FLOW_ASYNC_NODE_1() {
        System.out.println(Thread.currentThread().getName()+"1");
        return "1";
    }

    @Flow(value = "FLOW_ASYNC_NODE_2", retrieve = "message2")
    public String FLOW_ASYNC_NODE_2() {
        System.out.println(Thread.currentThread().getName()+"2");
        return "2";
    }

    @Flow(value = "FLOW_ASYNC_NODE_3", retrieve = "message3")
    public String FLOW_ASYNC_NODE_3() {
        System.out.println(Thread.currentThread().getName()+"3");
        return "3";
    }

    @Flow(value = "FLOW_ASYNC_CLOSE",retrieve = "end")
    public String FLOW_ASYNC_CLOSE() {
        System.out.println("FLOW_ASYNC_CLOSE");
        return "end";
    }

}
