package com.changgou;

import com.changgou.entity.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})
public class GoodsApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication9001.class);
    }

    @Value("${idWork.workerId:1}")
    private long workerId;
    @Value("${idWork.datacenterId:1}")
    private long datacenterId;

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(workerId, datacenterId);
    }
}
