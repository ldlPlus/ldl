package com.itheima.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ldl.plus
 * @date 2020年05月07日  10:48
 */
@FeignClient(value = "feign-provider")
public interface GoodsFeignConsumer {

}
