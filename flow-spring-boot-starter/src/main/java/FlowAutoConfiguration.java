import cc.eamon.open.flow.core.context.FlowInitializer;
import cc.eamon.open.flow.core.spring.ComponentScanner;
import cc.eamon.open.flow.core.spring.SpringUtils;
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
    public ComponentScanner componentScaner() {
        return new ComponentScanner();
    }

    @Bean
    public FlowInitializer flowDispose() {
        return new FlowInitializer();
    }

    @Bean
    public SpringUtils springUtil() {
        return new SpringUtils();
    }


}
