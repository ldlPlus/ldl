package com.changgou.user.feign;

import com.changgou.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ldl.plus
 * @date 2020年06月13日  13:48
 */
@FeignClient("user")
@RequestMapping("/user")
public interface UserFeign {

    @GetMapping("/load/{username}")
    User findUserInfo(@PathVariable("username") String username);
}
