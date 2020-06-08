package com.changgou.business.listener;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年06月07日  14:39
 */
@Component
public class AdListener {
    private static final Logger log = LoggerFactory.getLogger(AdListener.class);

    @RabbitListener(queues = "ad_update_queue")
    public void receiveMessage(String message) {
        log.info("接收到的消息为：" + message);

        // 发起远程调用
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://192.168.31.42/ad_update?position=" + message;
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 失败回调
                e.printStackTrace();
                log.error(url, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 成功回调
                log.info("请求成功：" + response.message());
            }
        });
    }
}
