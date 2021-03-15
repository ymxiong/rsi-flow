package cc.eamon.open.flow.core.check;

import cc.eamon.open.flow.core.check.context.ConfigContext;
import cc.eamon.open.flow.core.check.context.ContextHolder;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.strategy.ParseStrategy;
import cc.eamon.open.flow.core.check.strategy.ParseStrategyTypeEnum;
import cc.eamon.open.flow.core.check.structure.BasicItem;
import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.config.RouteConfig;

import java.util.List;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 参数检查入口
 */
public class CheckEntrance {

    private ConfigContext configContext;


    public CheckEntrance() {
    }

    /**
     * 参数校验入口
     *
     * @param path     配置文件根路径
     * @param flowName 待检验的主流程的名字
     * @return
     */
    private CheckResult check(String path, String flowName) {
        //Step1.读取所有配置文件进行前期准备
        configContext = new ConfigContext(path);
        //Step2.对目标流程进行校验
        CheckResult checkResult = new CheckResult();
        getPaths(configContext.getMainFlow(), checkResult);
        List<BasicItem> basicItems = checkResult.getPaths();
        //TODO:能否多线程优化
        for (BasicItem flowPath : basicItems) {
            checkOnePath(flowPath, checkResult);
        }
        return checkResult;
    }

//    /**
//     * Step1. 预准备工作，读取所有的配置文件装进FLowConfig
//     * @param path
//     */
//    private void prepared(String path,String flowName){
//        loadConfig(path);
//        mainFlow=flowConfigs.get(flowName);
//        for (RouteConfig route : mainFlow.getRoutes()) {
//            routeConfigMap.put(route.getId(),route);
//        }
//    }
//
//    /**
//     * Step1. 预准备工作，读取所有的配置文件装进FLowConfig
//     * @param path
//     */
//    private void loadConfig(String path){
//        for (FlowLoaderEnums value : FlowLoaderEnums.values()) {
//            // @Config自动扫描
//            Set<FlowConfig> configs = value.getLoader().load(path);
//            if (configs == null) continue;
//            for (FlowConfig config : configs) {
//                flowConfigs.put(config.getName(),config);
//            }
//        }
//    }

    /**
     * Step2.1 深度优先遍历得到所有路径
     * 嵌套的Flow肯定是符合规则的
     *
     * @param config
     * @param checkResult
     * @return
     */
    private CheckResult getPaths(FlowConfig config, CheckResult checkResult) {
        RouteConfig start = getStartNodeRouteConfig(config);
        ParseStrategy strategy = ParseStrategyTypeEnum.getStrategy(start.getType());

        strategy.parse(configContext, checkResult, null, start);
        return checkResult;
    }


    public RouteConfig getStartNodeRouteConfig(FlowConfig flowConfig) {
        RouteConfig startConfig = null;
        for (RouteConfig route : flowConfig.getRoutes()) {
            if (route.getType().equals("start")) ;
            startConfig = route;
        }
        return startConfig;
    }

    /**
     * Step2.2 具体检查某条路径
     *
     * @param checkResult
     * @return
     */
    private CheckResult checkOnePath(BasicItem basicItem, CheckResult checkResult) {
        //检查某条路径
        basicItem.check(checkResult);
        //每检查完一条路径之后需要清空上下文为下一次检查做准备
        ContextHolder.clear();
        return checkResult;
    }
}
