package cc.eamon.open.flow.core.stereotype.definition;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 18:30:21
 */
public interface FlowCode {

    String getName();

    String getRetrieve();

    String getScope();

    Method getMethod();

    Object execute(Object... args) throws Exception;




}
