package plus.ldl.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author ldl.plus
 * @date 2020年04月30日  17:51
 */
public class DirectListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("DirectListener1 = " + new String(message.getBody()));
    }
}