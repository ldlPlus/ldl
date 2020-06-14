package com.changgou.web.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

/**
 * @author ldl.plus
 * @date 2020年06月13日  16:07
 */
@Service
public class AuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 从cookie中获取jti
     *
     * @param request
     * @return
     */
    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie httpCookie = request.getCookies().getFirst("uid");
        if (httpCookie != null) {
            return httpCookie.getValue();
        }
        return null;
    }

    public String getJwtFromRedis(String jti) {
        return redisTemplate.boundValueOps(jti).get();
    }
}
