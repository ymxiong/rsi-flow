package cc.eamon.open.flow.spring.extra.pipeline;

import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.context.pipeline.BasePipeline;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.spring.stereotype.support.scan.ComponentFlowScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:06:46
 */
public class ExtraCodeLoadPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ExtraCodeLoadPipeline.class);

    private Map<String, FlowCode> flowCodes;

    private String[] basePackages;

    public ExtraCodeLoadPipeline(int level, Map<String, FlowCode> flowCodes, String... basePackages) {
        super(level);
        this.flowCodes = flowCodes;
        this.basePackages = basePackages;
    }

    @Override
    public void initializerSelf() {
        // 枚举扫描器
        logger.info("执行CodeLoadPipeline组装过程");
        ComponentFlowScanner componentFlowScanner = new ComponentFlowScanner();
        Set<? extends FlowCode> flowCodeSet = componentFlowScanner.scan(basePackages);
        for (FlowCode flowCode : flowCodeSet) {
            flowCodes.put(flowCode.getName(), flowCode);
        }
        Initializer.addExtraFlowCode(flowCodes);
    }
}
