package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.scan.FlowScanner;
import cc.eamon.open.flow.core.stereotype.support.enums.ScannerEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:06:46
 */
public class CodeLoadPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(CodeLoadPipeline.class);

    private Map<String, FlowCode> flowCodes;

    private String[] basePackages;

    public CodeLoadPipeline(int level, Map<String, FlowCode> flowCodes, String... basePackages) {
        super(level);
        this.flowCodes = flowCodes;
        this.basePackages = basePackages;
    }

    @Override
    public void initializerSelf() {
        // 枚举扫描器
        logger.info("执行CodeLoadPipeline组装过程");
        FlowScanner classpath_scan = ScannerEnums.CLASSPATH_SCANNER.getScanner();
        Set<? extends FlowCode> flowCodeSet = classpath_scan.scan(basePackages);
        for (FlowCode flowCode : flowCodeSet) {
            flowCodes.put(flowCode.getName(), flowCode);
        }
    }
}
