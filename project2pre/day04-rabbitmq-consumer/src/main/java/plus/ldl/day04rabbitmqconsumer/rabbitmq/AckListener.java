package plus.ldl.day04rabbitmqconsumer.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年05月04日  11:26
 */
public class AckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            System.out.println("AckListener.onMessage.......");
            channel.basicAck(deliveryTag, true);
        } catch (IOException e) {
            channel.basicNack(deliveryTag, true, true);
        }

    }
}
