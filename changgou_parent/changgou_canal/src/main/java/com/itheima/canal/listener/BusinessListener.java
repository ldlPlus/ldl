package com.itheima.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itheima.canal.config.RabbitMqConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ldl.plus
 * @date 2020年06月07日  14:02
 * canal监听类
 */
@CanalEventListener
public class BusinessListener {
    private static final Logger log = LoggerFactory.getLogger(BusinessListener.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 监听数据库
     *
     * @param entryType 当前操作数据库的类型
     * @param rowData   当前操作数据库的数据
     */
    @ListenPoint(schema = "changgou_business", table = "tb_ad")
    public void adUpdate(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        // log.info("广告表数据发生改变   改变之前的数据 ");
        // 改变之前的数据
        // rowData.getBeforeColumnsList().forEach(column -> log.info(column.getName() + " :: " + column.getValue()));

        // log.info("广告表数据发生改变   改变之后的数据 ");
        // 改变之后的数据
        // rowData.getAfterColumnsList().forEach(column -> log.info(column.getName() + " :: " + column.getValue()));
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            // 只关注position列
            if ("position".equals(column.getName())) {
                log.info("发送最新的数据到MQ：" + column.getValue());
                // 发送消息
                rabbitTemplate.convertAndSend("", RabbitMqConfig.AD_UPDATE_QUEUE, column.getValue());
            }
        }
    }
}
