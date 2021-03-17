package cc.eamon.open.flow.core.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/17 11:18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FlowComponent {
    String value() default "";
}
