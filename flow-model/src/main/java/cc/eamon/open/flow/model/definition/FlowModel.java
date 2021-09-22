package cc.eamon.open.flow.model.definition;


/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/15 14:32
 */
public interface FlowModel {

    String getId();

    String getType();

    Object check(Object object);


}
