package plus.ldl.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年05月07日  17:09
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
