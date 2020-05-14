package plus.ldl.day03springbootproducers.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年04月30日  18:11
 * 配置
 */
@Configuration
// @ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfig {

    @Value("spring.rabbitmq.TOPIC_EXCHANGE_NAME")
    public static final String TOPIC_EXCHANGE_NAME = "boot_topic_exchange";

    @Value("spring.rabbitmq.TOPIC_QUEUE_NAME")
    public static final String TOPIC_QUEUE_NAME = "boot_queue";

    /**
     * 交换机
     *
     * @return 交换机
     */
    @Bean
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 队列
     *
     * @return 队列
     */
    @Bean
    public Queue bootQueue() {
        return QueueBuilder.durable(TOPIC_QUEUE_NAME).build();
    }

    /**
     * 绑定
     *
     * @return 绑定
     */
    @Bean
    public Binding bindQueueExchange(
            @Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
