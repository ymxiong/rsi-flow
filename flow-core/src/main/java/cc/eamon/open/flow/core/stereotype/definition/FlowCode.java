package cc.eamon.open.flow.core.stereotype.definition;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 18:30:21
 */
public interface FlowCode {

    String getName();

    String getRetrieve();

    String getScope();

    List<Class<?>> getParameterTypes();

    Object execute(Object... args) throws Exception;

}
