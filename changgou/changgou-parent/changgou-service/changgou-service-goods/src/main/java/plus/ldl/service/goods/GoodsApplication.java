package plus.ldl.service.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ldl.plus
 * @date 2020年06月02日  10:38
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "plus.ldl.service.goods.mapper")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
