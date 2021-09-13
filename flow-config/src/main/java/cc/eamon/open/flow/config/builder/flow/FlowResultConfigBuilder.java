package cc.eamon.open.flow.config.builder.flow;

import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class FlowResultConfigBuilder {

    /**
     * 该结果的名称
     */
    private String name;

    /**
     * 该结果的类型
     */
    private String type;

    /**
     * 结果存放到的域
     */
    private String scope;

    /**
     * 解析目标
     */
    private String parse;

    public FlowResultConfig build() {
        validate();
        FlowResultConfig flowResultConfig = new FlowResultConfig();
        flowResultConfig.setName(name);
        flowResultConfig.setType(type);
        flowResultConfig.setScope(scope);
        flowResultConfig.setParse(parse);
        return flowResultConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }


    public FlowResultConfigBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public FlowResultConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public FlowResultConfigBuilder addScope(String scope) {
        this.scope = scope;
        return this;
    }

    public FlowResultConfigBuilder addParse(String parse) {
        this.parse = parse;
        return this;
    }


}
