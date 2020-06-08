package com.changgou.search.listener;

import com.changgou.search.config.RabbitMqConfig;
import com.changgou.search.service.ESManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ldl.plus
 * @date 2020年06月07日  20:02
 */
@Component
public class GoodsUpListener {

    @Autowired
    private ESManagerService esManagerService;

    private static final Logger log = LoggerFactory.getLogger(GoodsUpListener.class);

    @RabbitListener(queues = RabbitMqConfig.SEARCH_ADD_QUEUE)
    public void receiveUp(String spuId) {
        log.info("接收到的消息为：" + spuId);
        esManagerService.importDataBySpuId(spuId);
    }

}
