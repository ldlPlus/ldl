package com.changgou.web.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ldl.plus
 */
@SpringBootApplication
@EnableEurekaClient
public class WebGateWayApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(WebGateWayApplication8001.class, args);
    }
}
