package plus.ldl.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 * @date 2020年05月14日  18:34
 */
@SpringBootApplication
@EnableEurekaClient
public class UserProviderMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderMain9001.class, args);
    }
}
