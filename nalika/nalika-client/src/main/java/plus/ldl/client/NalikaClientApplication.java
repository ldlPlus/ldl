package plus.ldl.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ldl.plus
 * @date 2020年05月25日  15:33
 */
@SpringBootApplication
@EnableScheduling
public class NalikaClientApplication {
    public static void main(String[] args) {
            SpringApplication.run(NalikaClientApplication.class, args);
        }
}
