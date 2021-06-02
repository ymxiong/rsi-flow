package cc.eamon.open.flow.spring.stereotype.support.scan;

import cc.eamon.open.flow.core.context.Initializer;
import cc.eamon.open.flow.core.error.PackageNotFoundException;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.scan.FlowScanner;
import cc.eamon.open.flow.core.stereotype.support.defination.LocalJavaCode;
import cc.eamon.open.flow.core.stereotype.support.defination.LocalJavaCodeProxy;
import cc.eamon.open.flow.core.utils.ClassUtils;
import cc.eamon.open.flow.spring.ComponentScanner;
import cc.eamon.open.flow.spring.stereotype.support.defination.SpringCode;
import cc.eamon.open.flow.spring.stereotype.support.defination.SpringCodeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO: Simple Flow Code TO Flow Code
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 19:45:48
 */
public class ComponentFlowScanner implements FlowScanner {

    private static Logger logger = LoggerFactory.getLogger(ComponentFlowScanner.class);

    @Override
    public Set<? extends FlowCode> scan(String... basePackages) {
        return doScan(basePackages);
    }

    private Set<SpringCodeProxy> doScan(String... basePackages) {
        if (basePackages == null) {
            String errorInfo = "At least one base package must be specified";
            logger.info(errorInfo);
            throw new PackageNotFoundException(errorInfo);
        }
        Set<SpringCodeProxy> flowDefinitions = new LinkedHashSet<>();

        for (String basePackage : basePackages) {
            Set<SpringCode> candidates = findCandidateFlows(basePackage);
            for (SpringCode candidate : candidates) {
                flowDefinitions.add(new SpringCodeProxy(candidate));
            }
        }
        return flowDefinitions;
    }

    private Set<SpringCode> findCandidateFlows(String basePackage) {

        Set<SpringCode> flowItemSet = new LinkedHashSet<>();

        List<Class<?>> classes = ClassUtils.getAllClassByPackageName(basePackage);
        for (Class<?> aClass : classes) {
            for (Method aClassMethod : aClass.getMethods()) {
                Flow flow = aClassMethod.getAnnotation(Flow.class);
                if (flow == null) continue;
                flowItemSet.add(new SpringCode(ComponentScanner.flowComponentMap.get(aClass.getName()), aClassMethod, flow));
            }
        }

        return flowItemSet;
    }


}
