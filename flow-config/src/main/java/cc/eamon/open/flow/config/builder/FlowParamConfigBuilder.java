package cc.eamon.open.flow.config.builder;

import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-09-13 16:35:54
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

    public FlowParamConfig build() {
        validate();
        FlowParamConfig flowParamConfig = new FlowParamConfig();
        flowParamConfig.setName(name);
        return flowParamConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }

}
