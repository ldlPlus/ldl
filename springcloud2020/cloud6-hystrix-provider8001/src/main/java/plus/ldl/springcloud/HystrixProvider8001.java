package plus.ldl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月07日  20:37
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixProvider8001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixProvider8001.class, args);
    }
}
