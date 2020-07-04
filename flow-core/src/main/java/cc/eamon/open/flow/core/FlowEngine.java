package cc.eamon.open.flow.core;

import cc.eamon.open.flow.core.context.DisposeContext;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.route.structure.FlowNode;
import cc.eamon.open.flow.core.stereotype.support.FlowHolder;


/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 15:14:46
 */
public class FlowEngine {

    private DisposeContext disposeContext = new DisposeContext();

    public static FlowEngine instance() {
        // 保障同一线程仅有单一执行的流程引擎
        if (FlowContextHolder.get() != null && FlowContextHolder.get().getFlowEngine() == null) {
            synchronized (FlowEngine.class) {
                if (FlowContextHolder.get().getFlowEngine() == null) {
                    FlowContextHolder.get().setFlowEngine(new FlowEngine());
                }
            }

        }
        return FlowContextHolder.get().getFlowEngine();
    }

    private FlowEngine() {
    }

    public FlowEngine run(String flowName) {
        FlowHolder flowHolder = disposeContext.getFlowHolders().get(flowName);
        flowHolder.execute();
        return this;
    }

    @Deprecated
    public FlowEngine run(FlowNode node) {

//        //TODO：接入谢帅宇的路由功能(应该是在代理工厂中接入)
//        try {
////        	if((node.getType()).equals("end"))//结束
////        		return this;
//            RouteEngine routeEngine = new RouteEngine();
//            RouteEngine proxy = (RouteEngine) new ProxyFactory(routeEngine).getProxyInstance();
//            List<FlowNode> nextNodes = proxy.getNextNode(node.getNodeId());
//            if (nextNodes.size() == 0)
//                return this;
//            run(nextNodes.get(0));//TODO 并行之后考虑多个出口
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return this;
    }

    /**
     * 注入参数
     *
     * @param paramName
     * @param paramValue
     * @return
     */
    public FlowEngine register(String paramName, Object paramValue) {
        //注入参数到上下文中
        FlowContextHolder.get().getVariableMap().put(paramName, paramValue);
        FlowContextHolder.get().getVariableTypeMap().put(paramName, paramValue.getClass());
        return this;
    }

    public FlowEngine release(Object... param) {
        return this;
    }

    public void release() {
        FlowContextHolder.clear();
    }

    /**
     * 取得参数
     *
     * @param paramName
     * @return
     */
    public Object retrieve(String paramName) {
        return FlowContextHolder.get().getVariableMap().get(paramName);
    }

}
