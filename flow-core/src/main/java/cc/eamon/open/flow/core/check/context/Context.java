package cc.eamon.open.flow.core.check.context;

import cc.eamon.open.flow.core.check.structure.BasicItem;
import cc.eamon.open.flow.config.FlowConfig;
import cc.eamon.open.flow.config.RouteConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    /**
     * 基础配置Map
     */
    private Map<String, FlowConfig> flowConfigMap = new HashMap<>();

    /**
     * 当前的RouteConfigMap
     */
    private Map<String, RouteConfig> routeConfigMap = new HashMap<>();

    /**
     * 校验期间的paramContext
     */
    private ParamContext paramContext;

    /**
     * 解析拿到的所有路径
     */
    private List<BasicItem> paths;
}
