package com.changgou.business.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ldl.plus
 * @date 2020年06月07日  14:39
 */
@Component
public class AdListener {
    private static final Logger log = LoggerFactory.getLogger(AdListener.class);

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = "ad_update_queue")
    public void receiveMessage(String message) {
        log.info("接收到的消息为：" + message);


        // 发起远程调用
        String url = "http://www.ldl.plus/ad_update?position=" + message;
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("请求成功：" + entity.getBody());
        } else {
            log.error(url, entity.getBody());
        }
        // OkHttpClient okHttpClient = new OkHttpClient();
        // Request request = new Request.Builder().url(url).build();
        // Call call = okHttpClient.newCall(request);
        // call.enqueue(new Callback() {
        //     @Override
        //     public void onFailure(Call call, IOException e) {
        //         // 失败回调
        //         e.printStackTrace();
        //         log.error(url, e);
        //     }
        //
        //     @Override
        //     public void onResponse(Call call, Response response) throws IOException {
        //         // 成功回调
        //         log.info("请求成功：" + response.message());
        //     }
        // });
    }
}
