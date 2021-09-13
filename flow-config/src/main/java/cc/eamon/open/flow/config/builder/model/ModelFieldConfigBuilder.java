package cc.eamon.open.flow.config.builder.model;

import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.model.ModelFiledConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class ModelFieldConfigBuilder {

    /**
     * 模型属性名
     */
    private String name;

    /**
     * 模型属性类型
     */
    private String type;

    public ModelFiledConfig build() {
        validate();
        ModelFiledConfig modelFiledConfig = new ModelFiledConfig();
        modelFiledConfig.setName(name);
        modelFiledConfig.setType(type);
        return modelFiledConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }


    public void addName(String name) {
        this.name = name;
    }

    public void addType(String type) {
        this.type = type;
    }
}
