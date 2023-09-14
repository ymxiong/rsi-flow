package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncAllCode {

    @Flow(value = "FLOW_ASYNC_START")
    public void FLOW_ASYNC_START() {
        System.out.println("ASYNC_START");
        FlowEngine.instance().register("start", "start");
    }

    @Flow(value = "FLOW_ASYNC_NODE_1")
    public void FLOW_ASYNC_NODE_1() {
        System.out.println(Thread.currentThread().getName() + "1");
        FlowEngine.instance().register("message1", "1");
    }

    @Flow(value = "FLOW_ASYNC_NODE_2")
    public void FLOW_ASYNC_NODE_2() {
        System.out.println(Thread.currentThread().getName() + "2");
        FlowEngine.instance().register("message2", "2");
    }

    @Flow(value = "FLOW_ASYNC_NODE_3")
    public void FLOW_ASYNC_NODE_3() {
        System.out.println(Thread.currentThread().getName() + "3");
        FlowEngine.instance().register("message3", "3");
    }

    @Flow(value = "FLOW_ASYNC_CLOSE")
    public void FLOW_ASYNC_CLOSE() {
        System.out.println("FLOW_ASYNC_CLOSE");
        FlowEngine.instance().register("end", "end");
    }

}
