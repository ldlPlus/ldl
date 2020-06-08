package com.itheima.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itheima.canal.config.RabbitMqConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author ldl.plus
 * @date 2020年06月07日  15:26
 * 商品上架状态监听
 */
@CanalEventListener
public class SpuListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ListenPoint(schema = "changgou_goods", table = "tb_spu")
    public void goodsUp(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        // 获取改变之前的数据
        HashMap<String, String> oldData = new HashMap<>();
        rowData.getBeforeColumnsList().forEach(column -> oldData.put(column.getName(), column.getValue()));
        // 获取改变之后的数据
        HashMap<String, String> newData = new HashMap<>();
        rowData.getAfterColumnsList().forEach(column -> newData.put(column.getName(), column.getValue()));

        // 获取最新上架的商品 0->1
        if ("0".equals(oldData.get("is_marketable")) && "1".equals(newData.get("is_marketable"))) {
            // 最新上架商品 将商品的spuId发送到mq
            rabbitTemplate.convertAndSend(RabbitMqConfig.GOODS_UP_EXCHANGE, "", newData.get("id"));
        }

        // 最新下架的商品 1->0
        if ("1".equals(oldData.get("is_marketable")) && "0".equals(newData.get("is_marketable"))) {
            // 最新上架商品 将商品的spuId发送到mq
            rabbitTemplate.convertAndSend(RabbitMqConfig.GOODS_DOWN_EXCHANGE, "", newData.get("id"));
        }
    }
}
