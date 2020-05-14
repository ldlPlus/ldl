package plus.ldl.day01springboot;

import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Day01SpringbootApplication.class)
class Day01SpringbootApplicationTests {

    @Test
    void contextLoads() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);
        try {
            tomcat.addWebapp("/", "D:\\");
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
