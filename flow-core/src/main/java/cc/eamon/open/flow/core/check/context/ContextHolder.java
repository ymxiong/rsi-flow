//package cc.eamon.open.flow.core.check.context;
//
//public class ContextHolder {
//    /**
//     * init Context for current thread
//     */
//    private static ThreadLocal<ParamContext> localParamContext = ThreadLocal.withInitial(ParamContext::new);
//
//    public ContextHolder() {
//    }
//
//    public static ParamContext get() {
//        return localParamContext.get();
//    }
//
//    public static void init() {
//        if (localParamContext.get() == null) {
//            localParamContext.set(new ParamContext());
//        }
//    }
//
//    public static void clear() {
//        localParamContext.remove();
//    }
//
//}
