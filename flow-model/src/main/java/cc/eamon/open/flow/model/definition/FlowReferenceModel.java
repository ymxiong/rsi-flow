package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.model.ModelEngine;
import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowReferenceModel implements FlowModel {

    private static Logger logger = LoggerFactory.getLogger(FlowReferenceModel.class);

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
        if (referenceClazz.equals(object.getClass()) || referenceClazz.isAssignableFrom(object.getClass()))
            return object;
        // 可能是Map需要进行转换
        try {
            object = JSONObject.parseObject(JSONObject.toJSONString(object), referenceClazz);
        } catch (Exception e) {
            logger.info("无法将类型为【" + object.getClass() + "】的对象用JSONObject解析为类型【" + referenceClazz.getClass() + "】的对象");
            throw e;
        }
        return object;
    }


}
