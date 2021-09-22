package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import com.alibaba.fastjson.JSONObject;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowReferenceModel implements FlowModel {

    private String id;

    private String reference;

    private Class referenceClazz;


    public FlowReferenceModel(String id, String reference, Class referenceClazz) {
        this.id = id;
        this.reference = reference;
        this.referenceClazz = referenceClazz;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return FlowModelTypeEnums.FLOW_REFERENCE_MODEL.getType();
    }

    @Override
    public Object check(Object object) {
        // 检查Object是否是该类型
        if (referenceClazz.equals(object.getClass())) return object;
        // 可能是Map需要进行转换
        object = JSONObject.parseObject(JSONObject.toJSONString(object), referenceClazz);
        return object;
    }


}
