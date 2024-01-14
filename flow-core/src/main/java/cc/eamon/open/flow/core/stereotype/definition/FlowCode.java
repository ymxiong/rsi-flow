package cc.eamon.open.flow.core.stereotype.definition;

import cc.eamon.open.flow.model.enums.TypeEnums;

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

    List<TypeEnums> getParamTypes();

    Object execute(Object... args) throws Exception;

}
