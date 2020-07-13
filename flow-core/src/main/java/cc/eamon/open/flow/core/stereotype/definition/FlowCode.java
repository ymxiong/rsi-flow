package cc.eamon.open.flow.core.stereotype.definition;

import java.lang.reflect.Method;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-03 18:30:21
 */
public interface FlowCode{

    FlowCode getFlowCode();

    String getFlowName();

    void execute(Object... args);

    // TODO: NEED OPTIMIZE
    Method getMethod();

}
