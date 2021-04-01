package cc.eamon.open.flow.config;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-02 18:56:34
 */
public class FlowConfig {

    /**
     * 流程id
     */
    private String id;

    /**
     * 流程名
     */
    private String name;

    /**
     * 流程类型
     */
    private String type;

    /**
     * 流程节点路由表
     */
    private List<RouteConfig> routes;

    public FlowConfig(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public FlowConfig(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public FlowConfig(String id, String name, String type, List<RouteConfig> routes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RouteConfig> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteConfig> routes) {
        this.routes = routes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
