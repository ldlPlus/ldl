package plus.ldl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ldl.plus
 * @date 2020年05月29日  17:12
 */
@SpringBootApplication(scanBasePackages = "plus.ldl")
public class EduServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }
}
