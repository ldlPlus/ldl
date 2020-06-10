package com.changgou.page.listener;

import com.changgou.page.config.RabbitMqConfig;
import com.changgou.page.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年06月10日  14:45
 */
@Component
public class PageListener {

    private static final Logger log = LoggerFactory.getLogger(PageListener.class);

    @Autowired
    private PageService pageService;

    @RabbitListener(queues = RabbitMqConfig.PAGE_CREATE_QUEUE)
    public void receiveMessage(String spuId) throws IOException {
        log.info("获取静态化页面的商品Id，Id的值为： " + spuId);
        pageService.generateHtml(spuId);
    }
}
