package plus.ldl.eruekeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaServer
public class EruekeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruekeServerApplication.class, args);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
