package cc.eamon.open.flow.core.stereotype.auto;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/8/11 10:21
 */
@Target({ElementType.TYPE})
@Import(ConfigScannerRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigScan {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}