package plus.ldl.day04rabbitmqproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ldl.plus
 * @date 2020年05月04日  10:57
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("confirm").build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange("topic_exchange").durable(true).build();
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("*.confirm.*").noargs();
    }

    @Bean
    public Exchange exchange1() {
        return ExchangeBuilder.topicExchange("order_exchange").durable(true).build();
    }

    @Bean
    public Queue queue1() {
        // return QueueBuilder.durable("order_queue").build();
        return QueueBuilder.durable("order_queue").ttl(10000).deadLetterExchange("dead-exchange").deadLetterRoutingKey("dead.routingKing").build();
    }

    @Bean
    public Binding binding1(@Qualifier("queue1") Queue queue, @Qualifier("exchange1") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("order.#").noargs();
    }

    // 死信队列

    @Bean
    public Exchange exchange2() {
        return ExchangeBuilder.topicExchange("dead-exchange").durable(true).build();
    }

    @Bean
    public Queue queue2() {
        return QueueBuilder.durable("dead-queue").build();
    }

    @Bean
    public Binding binding2(@Qualifier("queue2") Queue queue, @Qualifier("exchange2") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.dead.#").noargs();
    }

}
