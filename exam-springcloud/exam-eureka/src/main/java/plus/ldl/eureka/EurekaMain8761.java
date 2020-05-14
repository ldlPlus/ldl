package plus.ldl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ldl.plus
 * @date 2020年05月14日  18:16
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain8761 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain8761.class, args);
    }
}
