package cc.eamon.open.flow.core.stereotype.support.defination;

import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-25 19:55:22
 */
public class RemoteHttpCode implements FlowCode {

    private static Logger logger = LoggerFactory.getLogger(LocalJavaCode.class);

    public RemoteHttpCode() {
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getRetrieve() {
        return "";
    }

    @Override
    public String getScope() {
        return "";
    }

    @Override
    public List<Class<?>> getParameterTypes() {
        return new ArrayList<>();
    }


    @Override
    public Object execute(Object... args) throws Exception {
        return null;
    }

}
