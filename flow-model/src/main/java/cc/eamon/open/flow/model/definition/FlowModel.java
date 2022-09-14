package cc.eamon.open.flow.model.definition;


import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public interface FlowModel {

    String getId();

    String getType();

    void check(Object object);

    Object build(String name, String modelType, Map<String, Object> variables);

    Object parse(Object variable);


}
