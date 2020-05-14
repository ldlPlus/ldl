package plus.ldl.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月05日  11:23
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class Consumer80 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80.class, args);
    }
}
