package plus.ldl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ldl.plus
 * @date 2020年05月05日  12:04
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka8671 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka8671.class, args);
    }
}
