package cc.eamon.open.flow.core.context;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 00:48:38
 */
public class FlowContextHolder {

    /**
     * init Context for current thread
     */
    private static ThreadLocal<FlowContext> local = ThreadLocal.withInitial(FlowContext::new);

    private FlowContextHolder() {
    }

    public static FlowContext get() {
        if (local.get() == null) {
            local.set(new FlowContext());
        }
        return local.get();
    }

    public static void clear() {
        local.remove();
    }

}
