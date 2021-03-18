package cc.eamon.open.flow.core.spring;

import cc.eamon.open.flow.core.stereotype.support.defination.SimpleFlowCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/17 11:12
 */
public class ComponentScaner implements BeanPostProcessor, PriorityOrdered {

    private static final Logger logger = LoggerFactory.getLogger(ComponentScaner.class);

    public static Map<String, Object> flowComponentMap = new HashMap<String, Object>();

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        if (clazz.isAnnotationPresent(FlowComponent.class)){
            logger.info("FlowComponent [{}] has been found",beanName);
            flowComponentMap.put(clazz.getName(),bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
