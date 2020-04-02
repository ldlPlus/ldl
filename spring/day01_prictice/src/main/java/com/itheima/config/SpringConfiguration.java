package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ldl.plus
 * @date 2020年03月10日  19:15
 */
@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class)
public class SpringConfiguration {

}
