package plus.ldl.day02enable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableVip
public class Day02EnableApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Day02EnableApplication.class, args);
        context.getBean("plus.ldl.day02enable.VipBean");
    }

}
