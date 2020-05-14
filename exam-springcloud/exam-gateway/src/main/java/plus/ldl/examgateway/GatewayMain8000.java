package plus.ldl.examgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月14日  18:39
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain8000.class, args);
    }
}
