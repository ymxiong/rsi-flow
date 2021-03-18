import cc.eamon.open.flow.core.spring.ComponentScaner;
import cc.eamon.open.flow.core.util.LogoUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/17 11:02
 */

@Configuration
public class FlowAutoConfiguration {

    static {
        LogoUtil.print();
    }

    @Bean
    public ComponentScaner componentScaner(){
        return new ComponentScaner();
    }


}
