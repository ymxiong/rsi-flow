package cc.eamon.open.flow.config.builder.model;

import cc.eamon.open.flow.config.flow.FlowRouteConfig;
import cc.eamon.open.flow.config.model.ModelConfig;
import cc.eamon.open.flow.config.model.ModelFiledConfig;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;
import java.util.List;

/**
 * Author: jvjv
 * Email: 1181370590@qq.com
 * Time: 2021-09-13 21:35:54
 */
public class ModelConfigBuilder {

    /**
     * 模型id
     */
    private String id;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 模型参考
     */
    private String reference;


    private List<ModelFiledConfig> fields;

    public ModelConfig build() {
        validate();
        ModelConfig modelConfig = new ModelConfig();
        modelConfig.setId(id);
        modelConfig.setType(type);
        modelConfig.setReference(reference);
        modelConfig.setFields(fields);
        return modelConfig;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(build(), SerializerFeature.PrettyFormat);
    }

    private void validate() {

    }

    public ModelConfigBuilder addId(String id) {
        this.id = id;
        return this;
    }

    public ModelConfigBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public ModelConfigBuilder addReference(String reference) {
        this.reference = reference;
        return this;
    }

    public ModelConfigBuilder addFields(ModelFiledConfig... fields) {
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }
}
