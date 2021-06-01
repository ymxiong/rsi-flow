package cc.eamon.open.flow.core.context.pipeline;

import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.support.enums.ScannerEnums;

import java.util.Map;
import java.util.Set;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:06:46
 */
public class CodeLoadPipeline extends BasePipeline {

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
        for (ScannerEnums value : ScannerEnums.values()) {
            // @FlowScan自动扫描
            Set<? extends FlowCode> flowCodeSet = value.getScanner().scan(basePackages);
            if (flowCodeSet == null) continue;
            for (FlowCode flowCode : flowCodeSet) {
                flowCodes.put(flowCode.getName(), flowCode);
            }
        }
    }
}
