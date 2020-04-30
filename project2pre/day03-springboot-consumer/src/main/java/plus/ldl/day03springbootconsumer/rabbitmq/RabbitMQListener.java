package plus.ldl.day03springbootconsumer.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ldl.plus
 * @date 2020年04月30日  18:31
 * 监听消息
 */
@Component
public class RabbitMQListener {

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue1(Message message) {
        System.out.println("RabbitMQListener.listenerQueue1: " + new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue2(Message message) {
        System.out.println("RabbitMQListener.listenerQueue2: " + new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue3(Message message) {
        System.out.println("RabbitMQListener.listenerQueue3: " + new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue4(Message message) {
        System.out.println("RabbitMQListener.listenerQueue4: " + new String(message.getBody()));
    }
}
