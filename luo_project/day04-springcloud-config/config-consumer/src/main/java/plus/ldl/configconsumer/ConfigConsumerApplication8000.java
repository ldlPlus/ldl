package plus.ldl.configconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author ldl.plus
 * @date 2020年05月25日  10:26
 */
@SpringBootApplication
@RefreshScope
public class ConfigConsumerApplication8000 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigConsumerApplication8000.class, args);
    }
}
