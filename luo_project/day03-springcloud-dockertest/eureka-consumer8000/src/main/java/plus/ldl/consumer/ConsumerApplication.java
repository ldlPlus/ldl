package plus.ldl.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ldl.plus
 * @date 2020年05月24日  16:56
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "plus.ldl.feign") //开启Feign的功能
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
