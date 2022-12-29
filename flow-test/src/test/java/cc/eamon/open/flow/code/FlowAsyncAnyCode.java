package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncAnyCode {

    @Flow(value = "FLOW_ASYNC_ANY_START",retrieve = "start")
    public void FLOW_ASYNC_ANY_START() {
        System.out.println("ASYNC_ANY_START");
        FlowEngine.instance().register("start", "start");
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_1", retrieve = "message1")
    public void FLOW_ASYNC_ANY_NODE_1() {
        System.out.println(Thread.currentThread().getName()+"1");
        FlowEngine.instance().register("message1", "1");
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_2", retrieve = "message2")
    public void FLOW_ASYNC_ANY_NODE_2() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FlowEngine.instance().register("message2", "2");
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_3", retrieve = "message3")
    public void FLOW_ASYNC_ANY_NODE_3() {
        try {
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName()+"3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FlowEngine.instance().register("message3", "3");
    }

    @Flow(value = "FLOW_ASYNC_ANY_CLOSE",retrieve = "end")
    public void FLOW_ASYNC_ANY_CLOSE() {
        System.out.println("FLOW_ASYNC_CLOSE");
        FlowEngine.instance().register("result", "end");
    }

}
