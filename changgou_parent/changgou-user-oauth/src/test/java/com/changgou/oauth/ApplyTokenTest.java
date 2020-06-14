package com.changgou.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月13日  11:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplyTokenTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     *
     */
    @Test
    public void test15() throws Exception {
        // 构建请求地址
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-auth");
        String url = serviceInstance.getUri() + "/oauth/token";

        // 封装请求参数
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", "itheima");
        body.add("password", "itheima");
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", this.getHttpBasic("changgou", "changgou"));
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

        // 发送请求
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        MultiValueMap<String, String> map = requestEntity.getBody();
        System.out.println("map = " + map);
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String value = clientId + ":" + clientId;
        String encode = Base64Utils.encodeToString(value.getBytes());
        return "Basic " + encode;
    }
}
