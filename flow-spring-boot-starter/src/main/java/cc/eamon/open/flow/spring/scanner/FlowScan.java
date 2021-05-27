package cc.eamon.open.flow.spring.scanner;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-07-01 21:36:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//这个类交给Spring管理
@Import(FlowScannerRegistrar.class)
public @interface FlowScan {

    String[] configPath() default {};

    String[] flowPath() default {};

}
