package cc.eamon.open.flow.core.stereotype.support;

import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.stereotype.config.FlowConfig;
import cc.eamon.open.flow.core.stereotype.config.RouteConfig;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:18:53
 */
public class FlowHolder {

    private FlowConfig flowConfig;


    private Map<String, FlowCode> flowCodes;

    public FlowHolder(FlowConfig flowConfig, Map<String, FlowCode> flowCodes) {
        this.flowConfig = flowConfig;
        this.flowCodes = flowCodes;
    }

    public String getFlowName() {
        return flowConfig.getName();
    }

    public Map<String, FlowCode> getFlowCodes() {
        return flowCodes;
    }



    // TODO: NEED OPTIMIZE THREAD RUN
    public void execute(){
//        FlowContextHolder.init();
        for (RouteConfig routeConfig:flowConfig.getRoutes()){
            if (routeConfig.getType().equals("start")) {
                //这里后续可能改动
                execute(routeConfig);
                break;
            }
        }
    }


    public void execute(RouteConfig routeConfig){
        //TODO:需要一个判断是否是结束节点的操作，目前写的是判断是否为空，待修改
        if (routeConfig==null) return;
        FlowCode flowCode=flowCodes.get(routeConfig.getName());
        Method method = flowCode.getMethod();
        //注入流程代码所需参数
        //拿到参数名称以及类型
        Class<?>[] parameterClasses = method.getParameterTypes();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);

        //注入参数
        assert parameterNames != null;
        int parameterNum = parameterNames.length;
        Object[] args = new Object[parameterNum];

        for (int i = 0; i < parameterNum; i++) {
            if (!parameterClasses[i].equals(FlowContextHolder.get().getVariableTypeMap().get(parameterNames[i]))) {
                //TODO: 参数匹配
                System.out.println("参数不匹配");
            }
            args[i] = FlowContextHolder.get().getVariableMap().get(parameterNames[i]);
        }

        flowCode.execute(args);
        //递归执行下一个节点
//        execute(Route.route(routeConfig));
    }


}
