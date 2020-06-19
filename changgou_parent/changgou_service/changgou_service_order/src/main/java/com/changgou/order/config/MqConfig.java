package com.changgou.order.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年06月19日  13:32
 */
@Configuration
public class MqConfig {

    public static final String ORDER_QUEUE = "order_queue";
    public static final String ORDER_ROUTING = "order_queue";
    public static final String ORDER_EXCHANGE = "order_exchange";

    /**
     * 创建队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public Exchange orderedExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding orderQueueExchange(Queue orderQueue, Exchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_ROUTING).noargs();
    }
}
