package plus.ldl.eurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月23日  11:57
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication8000 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication8000.class, args);
    }
}
