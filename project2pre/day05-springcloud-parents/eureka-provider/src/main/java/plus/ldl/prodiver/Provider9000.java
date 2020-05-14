package plus.ldl.prodiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月05日  11:25
 */
@SpringBootApplication
@EnableEurekaClient
public class Provider9000 {
    public static void main(String[] args) {
        SpringApplication.run(Provider9000.class, args);
    }
}
