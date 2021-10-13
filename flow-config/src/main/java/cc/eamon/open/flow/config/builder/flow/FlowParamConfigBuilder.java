package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowParamConfigBuilder {

    /**
     * 该参数的名称
     */
    private String name;

    /**
     * 该参数的类型
     */
    private String type;

    /**
     * 该参数所在的域
     */
    private String scope;

    /**
     * 该参数是否可以为空
     */
    private Boolean notnull;

    public FlowParamConfig build() {
        validate();
        FlowParamConfig flowParamConfig = new FlowParamConfig();
        flowParamConfig.setName(name);
        flowParamConfig.setType(type);
        flowParamConfig.setScope(scope);
        flowParamConfig.setNotnull(notnull);
        return flowParamConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }


    public FlowParamConfigBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FlowParamConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public FlowParamConfigBuilder addScope(String scope) {
        this.scope = scope;
        return this;
    }

    public FlowParamConfigBuilder addNotnull(Boolean notnull) {
        this.notnull = notnull;
        return this;
    }
}
