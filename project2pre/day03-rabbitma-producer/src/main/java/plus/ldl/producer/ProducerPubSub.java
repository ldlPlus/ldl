package plus.ldl.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ldl.plus
 * @date 2020年04月30日  11:47
 * rabbitmq提供者
 */
public class ProducerPubSub {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.31.247");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String testFanout = "testFanout";
        channel.exchangeDeclare(testFanout, BuiltinExchangeType.FANOUT, true, false, false, null);

        String testFanoutQueues1 = "testFanoutQueues1";
        channel.queueDeclare(testFanoutQueues1, true, false, false, null);
        String testFanoutQueues2 = "testFanoutQueues2";
        channel.queueDeclare(testFanoutQueues2, true, false, false, null);

        channel.queueBind(testFanoutQueues1, testFanout, "");
        channel.queueBind(testFanoutQueues2, testFanout, "");

        for (int i = 0; i < 20; i++) {
            String body = "日志信息：我第" + (i + 1) + "次访问了~~~~~";
            channel.basicPublish(testFanout, "", null, body.getBytes());
        }

        channel.close();
        connection.close();
    }
}
