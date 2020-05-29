package plus.ldl.configprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author ldl.plus
 * @date 2020年05月25日  10:27
 */
@SpringBootApplication
@RefreshScope
public class ConfigProviderApplication9000 {


    public static void main(String[] args) {
        SpringApplication.run(ConfigProviderApplication9000.class, args);
    }
}
