package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.core.stereotype.support.enums.TypeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:29:34
 */
public class HttpExecutable implements Executable {

    private static Logger logger = LoggerFactory.getLogger(HttpExecutable.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;


    public HttpExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
    }

    @Override
    public List<TypeEnums> getParamsTypes() {
        return new ArrayList<>();
    }

    @Override
    public void execute() {

    }
}

