package cc.eamon.open.flow.core.processor;

import cc.eamon.open.flow.core.stereotype.Flow;
import cc.eamon.open.flow.core.context.DisposeContext;

import java.lang.reflect.Method;
import java.util.List;


/**
 * @author: lucas
 * @date: 2020/6/29
 * @email: lucas@eamon.cc
 */

public class FlowProcessor {
    public static void annotationProcess(Class clazz){
        List<String> nodelist = DisposeContext.getNodeList();
        Method[] methods=clazz.getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Flow.class)!=null){
                Flow annotation=method.getAnnotation(Flow.class);
                if (nodelist.contains(annotation.value())){
                    DisposeContext.getNodeMap().put(annotation.value(),
                            method);
                }
            }
        }
    }
}
