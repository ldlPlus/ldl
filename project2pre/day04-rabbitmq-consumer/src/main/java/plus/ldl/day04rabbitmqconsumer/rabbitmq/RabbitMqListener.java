package plus.ldl.day04rabbitmqconsumer.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ldl.plus
 * @date 2020年05月04日  11:24
 */
@Component
public class RabbitMqListener {
    @RabbitListener(queues = "confirm")
    public void listenerQueue1(Message message) {
        System.out.println("RabbitMqListener.listenerQueue1" + new String(message.getBody()));
    }

}
