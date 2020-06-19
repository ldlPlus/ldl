package com.changgou.order.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.order.config.MqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月19日  13:45
 */
@Component
public class OrderMessageListener {

    @RabbitListener(queues = MqConfig.ORDER_QUEUE)
    public void getMessages(String message) {
        Map<String, String> messageMap = JSON.parseObject(message, Map.class);

        // 通讯标识
        String returnCode = messageMap.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            String resultCode = messageMap.get("result_code");
            String outTradeNo = messageMap.get("out_trade_no");
            // 支付成功，修改订单状态
            if ("SUCCESS".equals(resultCode)) {

            } else {
                // 支付失败，关闭订单
            }
        }
        // 业务结果

        // 交易流水号
    }
}
