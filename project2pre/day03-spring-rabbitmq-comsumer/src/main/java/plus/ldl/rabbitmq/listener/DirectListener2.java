package plus.ldl.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author ldl.plus
 * @date 2020年04月30日  17:52
 */
public class DirectListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("DirectListener2 = " + new String(message.getBody()));
    }
}