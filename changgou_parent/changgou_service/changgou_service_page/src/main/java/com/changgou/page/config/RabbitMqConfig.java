package com.changgou.page.config;

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
    public static final String PAGE_CREATE_QUEUE = "page_create_queue";

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

    @Bean(name = SEARCH_ADD_QUEUE)
    public Queue searchAddQueue() {
        return new Queue(SEARCH_ADD_QUEUE);
    }

    @Bean(name = SEARCH_DEL_QUEUE)
    public Queue searchDelQueue() {
        return new Queue(SEARCH_DEL_QUEUE);
    }

    @Bean(name = PAGE_CREATE_QUEUE)
    public Queue pageCreateQueue() {
        return new Queue(PAGE_CREATE_QUEUE);
    }

    /**
     * 申明交换机
     */
    @Bean(name = GOODS_UP_EXCHANGE)
    public Exchange goodsUpExchange() {
        return ExchangeBuilder.fanoutExchange(GOODS_UP_EXCHANGE).durable(true).build();
    }

    @Bean(name = GOODS_DOWN_EXCHANGE)
    public Exchange goodsDownExchange() {
        return ExchangeBuilder.fanoutExchange(GOODS_DOWN_EXCHANGE).durable(true).build();
    }

    /**
     * 绑定
     */
    @Bean
    public Binding goodsUpExchangeBinding(@Qualifier(SEARCH_ADD_QUEUE) Queue queue,
                                          @Qualifier(GOODS_UP_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding goodsDownExchangeBinding(@Qualifier(SEARCH_DEL_QUEUE) Queue queue,
                                            @Qualifier(GOODS_DOWN_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding pageCreateExchangeBinding(@Qualifier(PAGE_CREATE_QUEUE) Queue queue,
                                             @Qualifier(GOODS_UP_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
