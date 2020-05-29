package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ldl.plus
 * @date 2020年05月28日  11:01
 */
@SpringBootApplication
@EnableScheduling
public class NalikaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NalikaServerApplication.class, args);
    }
}
