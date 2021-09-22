package cc.eamon.open.flow.model.definition;

import cc.eamon.open.flow.model.enums.FlowModelTypeEnums;
import cc.eamon.open.flow.model.error.FlowModelNotMatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public class FlowUserDefineModel implements FlowModel {

    private String id;

    public FlowUserDefineModel(String id) {
        this.id = id;
    }

    private Map<String, FlowModel> fields = new HashMap<>();

    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return FlowModelTypeEnums.FLOW_USER_DEFINE_MODEL.getType();
    }


    public void addField(String name, FlowModel flowModel) {
        fields.put(name, flowModel);
    }


    @Override
    public Object check(Object object) {
        // 检查Map中的各个属性是否适合
        for (String property : fields.keySet()) {
            Object value = ((Map) object).get(property);
            //TODO：为空检查
            if (value == null) {
                throw new FlowModelNotMatchException();
            }
            FlowModel flowModel = fields.get(property);
            flowModel.check(value);
        }
        return object;
    }
}
