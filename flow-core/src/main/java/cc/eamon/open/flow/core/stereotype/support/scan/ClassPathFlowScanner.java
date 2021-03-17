package cc.eamon.open.flow.core.stereotype.support.scan;

import cc.eamon.open.flow.core.error.PackageNotFoundException;
import cc.eamon.open.flow.core.error.ParamNotMatchException;
import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.stereotype.definition.FlowCode;
import cc.eamon.open.flow.core.stereotype.scan.FlowScanner;
import cc.eamon.open.flow.core.stereotype.support.defination.SimpleFlowCode;
import cc.eamon.open.flow.core.stereotype.support.defination.SimpleFlowCodeProxy;
import cc.eamon.open.flow.core.util.ClassUtils;
import cc.eamon.open.flow.core.util.WrapperClassUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.util.Assert;

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
public class ClassPathFlowScanner implements FlowScanner {

    private static Logger logger = LoggerFactory.getLogger(ClassPathFlowScanner.class);

    @Override
    public Set<? extends FlowCode> scan(String... basePackages) {
        return doScan(basePackages);
    }

    private Set<SimpleFlowCodeProxy> doScan(String... basePackages) {
//        if (basePackages == null) {
//            logger.info("At least one base package must be specified");
//        }
//        Assert.notEmpty(basePackages, "At least one base package must be specified");
        if (basePackages == null) {
            String errorInfo = "At least one base package must be specified";
            logger.info(errorInfo);
            throw new PackageNotFoundException(errorInfo);
        }
        Set<SimpleFlowCodeProxy> flowDefinitions = new LinkedHashSet<>();

        for (String basePackage : basePackages) {
            Set<SimpleFlowCode> candidates = findCandidateFlows(basePackage);
            for (SimpleFlowCode candidate : candidates) {
                flowDefinitions.add(new SimpleFlowCodeProxy(candidate));
            }
        }
        return flowDefinitions;
    }

    private Set<SimpleFlowCode> findCandidateFlows(String basePackage) {

        Set<SimpleFlowCode> flowItemSet = new LinkedHashSet<>();

        List<Class<?>> classes = ClassUtils.getAllClassByPackageName(basePackage);
        for (Class<?> aClass : classes) {
            for (Method aClassMethod : aClass.getMethods()) {
                Flow flow = aClassMethod.getAnnotation(Flow.class);
                if (flow != null) {
                    SimpleFlowCode definition = new SimpleFlowCode(aClass, aClassMethod, flow);
                    flowItemSet.add(definition);
                }
            }
        }

        return flowItemSet;
    }


}
