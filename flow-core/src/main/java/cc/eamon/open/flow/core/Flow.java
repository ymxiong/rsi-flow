package cc.eamon.open.flow.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: eamon
 * @email: eamon@eamon.cc
 * Time: 2020-06-26 15:19:24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Flow {

    String value() default "";

    String retrieve() default "";

}
