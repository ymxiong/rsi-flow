package cc.eamon.open.flow.core.stereotype.support.enums;

import cc.eamon.open.flow.core.stereotype.scan.FlowScanner;
import cc.eamon.open.flow.core.stereotype.support.scan.ClassPathFlowScanner;
import cc.eamon.open.flow.core.stereotype.support.scan.FileSystemFlowScanner;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 20:13:49
 */
public enum ScannerEnums {

    CLASSPATH_SCANNER("classpath scan", new ClassPathFlowScanner()),

    FILESYSTEM_SCANNER("file system scan", new FileSystemFlowScanner());


    ScannerEnums(String name, FlowScanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    private String name;

    private FlowScanner scanner;

    public String getName() {
        return name;
    }

    public FlowScanner getScanner() {
        return scanner;
    }
}
