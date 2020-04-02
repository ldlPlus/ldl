package plus.ldl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ldl.plus
 * @date 2020年03月10日  16:09
 * 配置类
 * Configuration  申明当前类是一个配置类
 * ComponentScan 用于指定Spring在创建容器时要扫描的包
 * value与basePackages作用一样，都是要扫描的包
 */
@Configuration
@ComponentScan("plus.ldl")
@PropertySource("classpath:jdbc.properties")
@Import(JDBCConfig.class)
public class SpringConfiguration {

}
