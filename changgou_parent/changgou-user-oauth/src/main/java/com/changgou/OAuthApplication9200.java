package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.changgou.oauth.dao")
public class OAuthApplication9200 {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication9200.class, args);
    }

}
