package com.changgou.search.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年06月07日  14:26
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 定义队列名称
     */
    public static final String AD_UPDATE_QUEUE = "ad_update_queue";
    public static final String SEARCH_ADD_QUEUE = "search_add_queue";
    public static final String SEARCH_DEL_QUEUE = "search_del_queue";

    /**
     * 定义交换机名称
     */
    public static final String GOODS_UP_EXCHANGE = "goods_up_exchange";
    public static final String GOODS_DOWN_EXCHANGE = "goods_down_exchange";

    /**
     * 申明队列
     */
    @Bean
    public Queue adUpdateQueue() {
        return new Queue(AD_UPDATE_QUEUE);
    }

    @Bean
    public Queue searchAddQueue() {
        return new Queue(SEARCH_ADD_QUEUE);
    }

    @Bean
    public Queue searchDelQueue() {
        return new Queue(SEARCH_DEL_QUEUE);
    }

    /**
     * 申明交换机
     */
    @Bean
    public Exchange goodsUpExchange() {
        return ExchangeBuilder.fanoutExchange(GOODS_UP_EXCHANGE).durable(true).build();
    }

    @Bean
    public Exchange goodsDownExchange() {
        return ExchangeBuilder.fanoutExchange(GOODS_DOWN_EXCHANGE).durable(true).build();
    }

    /**
     * 绑定
     */
    @Bean
    public Binding goodsUpExchangeBinding(@Qualifier("searchAddQueue") Queue queue,
                                          @Qualifier("goodsUpExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding goodsDownExchangeBinding(@Qualifier("searchDelQueue") Queue queue,
                                            @Qualifier("goodsDownExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
