package com.changgou.oauth.service.impl;

import com.changgou.oauth.service.AuthService;
import com.changgou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ldl.plus
 * @date 2020年06月13日  11:34
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${auth.ttl:3600}")
    private Long ttl;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {

        ServiceInstance serviceInstance = loadBalancerClient.choose("user-auth");
        String url = serviceInstance.getUri().toString() + "/oauth/token";
        // 申请令牌
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", this.getHttpBasic(clientId, clientSecret));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // 当后端出现了401，400 后端不进行处理，直接返回前端
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 || response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        // 封装结果数据
        Map<String, String> resultMap = responseEntity.getBody();
        if (resultMap == null || resultMap.get("access_token") == null || resultMap.get("refresh_token") == null || resultMap.get("jti") == null) {
            // 申请失败
            throw new RuntimeException("申请令牌失败");
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(resultMap.get("access_token"));
        authToken.setRefreshToken(resultMap.get("refresh_token"));
        authToken.setJti(resultMap.get("jti"));

        // 讲jti作为redis的key，jwt为value
        stringRedisTemplate.boundValueOps(authToken.getJti()).set(authToken.getAccessToken(), ttl, TimeUnit.SECONDS);

        return authToken;
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String value = clientId + ":" + clientSecret;
        String encode = Base64Utils.encodeToString(value.getBytes());
        return "Basic " + encode;
    }
}
