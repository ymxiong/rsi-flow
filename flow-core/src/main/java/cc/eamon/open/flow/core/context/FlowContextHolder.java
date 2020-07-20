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

    public FlowContextHolder() {
    }

    public static FlowContext get() {
        return local.get();
    }

    public static void init() {
        if (local.get() == null) {
            local.set(new FlowContext());
        }
    }

    public static void clear() {
        local.remove();
    }


}
