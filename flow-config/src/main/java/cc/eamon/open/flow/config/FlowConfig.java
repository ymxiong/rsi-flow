package cc.eamon.open.flow.config;

import cc.eamon.open.flow.config.enums.FlowTypeEnums;

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
     * 流程类型
     */
    private String type;

    /**
     * 流程名
     */
    private String name;

    /**
     * 流程节点路由表
     */
    private List<RouteConfig> routes;


    public FlowConfig(String id) {
        this.id = id;
    }

    public FlowConfig(String id, FlowTypeEnums type) {
        this.id = id;
        this.type = type.getName();
    }

    public FlowConfig(String id, FlowTypeEnums type, List<RouteConfig> routes) {
        this.id = id;
        this.type = type.getName();
        this.routes = routes;
    }

    public FlowConfig(String id, FlowTypeEnums type, String name, List<RouteConfig> routes) {
        this.id = id;
        this.type = type.getName();
        this.name = name;
        this.routes = routes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(FlowTypeEnums type) {
        this.type = type.getName();
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
}
