package cc.eamon.open.flow.spring.config;

import cc.eamon.open.flow.spring.ComponentScanner;
import cc.eamon.open.flow.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/17 11:02
 */

@Configuration
public class FlowAutoConfiguration {

    @Bean
    public ComponentScanner componentScanner() {
        return new ComponentScanner();
    }


    @Bean
    public SpringUtils springUtil() {
        return new SpringUtils();
    }


}
