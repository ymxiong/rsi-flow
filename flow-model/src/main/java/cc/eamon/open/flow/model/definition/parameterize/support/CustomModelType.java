package cc.eamon.open.flow.model.definition.parameterize.support;

import cc.eamon.open.flow.model.definition.parameterize.ModelType;
import cc.eamon.open.flow.model.enums.TypeEnums;

import java.lang.reflect.Type;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2022/9/21 6:10 下午
 **/
public class CustomModelType implements ModelType {

    private final String typeName;

    public CustomModelType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public Type handle() {
        return TypeEnums.getType(typeName);
    }
}
