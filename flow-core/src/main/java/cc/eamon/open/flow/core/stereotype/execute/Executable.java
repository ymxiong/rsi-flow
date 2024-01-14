package cc.eamon.open.flow.core.stereotype.execute;

import cc.eamon.open.flow.model.enums.TypeEnums;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:20:15
 */
public interface Executable {

    List<TypeEnums> getParamsTypes();

    void execute() throws Exception;
}
