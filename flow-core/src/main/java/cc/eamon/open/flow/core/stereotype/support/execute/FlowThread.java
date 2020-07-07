package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.core.FlowEngine;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class FlowThread implements Runnable {
    private FlowHolder flowHolder;

    public FlowThread(FlowHolder flowHolder) {
        this.flowHolder = flowHolder;
    }
    @Override
    public void run() {
        FlowEngine engine=FlowEngine.instance();
        flowHolder.execute();

    }
}
