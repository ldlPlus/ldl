package plus.ldl.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ldl.plus
 * @date 2020年05月01日  23:22
 * 配置
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * LoadBalanced 注解是eureka集群
     *
     * @return
     */
    @Bean
    // @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
