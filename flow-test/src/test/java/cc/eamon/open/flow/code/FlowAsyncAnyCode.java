package cc.eamon.open.flow.code;

import cc.eamon.open.flow.core.stereotype.Flow;

public class FlowAsyncAnyCode {

    @Flow(value = "FLOW_ASYNC_ANY_START",retrieve = "start")
    public String FLOW_ASYNC_ANY_START() {
        System.out.println("ASYNC_ANY_START");
        return "start";
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_1", retrieve = "message1")
    public String FLOW_ASYNC_ANY_NODE_1() {
        System.out.println(Thread.currentThread().getName()+"1");
        return "1";
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_2", retrieve = "message2")
    public String FLOW_ASYNC_ANY_NODE_2() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "2";
    }

    @Flow(value = "FLOW_ASYNC_ANY_NODE_3", retrieve = "message3")
    public String FLOW_ASYNC_ANY_NODE_3() {
        try {
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName()+"3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "3";
    }

    @Flow(value = "FLOW_ASYNC_ANY_CLOSE",retrieve = "end")
    public String FLOW_ASYNC_ANY_CLOSE() {
        System.out.println("FLOW_ASYNC_CLOSE");
        return "end";
    }

}
