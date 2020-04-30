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
public class ProducerTopics {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.31.247");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String testTopic = "testTopic";
        channel.exchangeDeclare(testTopic, BuiltinExchangeType.TOPIC, true, false, false, null);

        String testTopicQueues1 = "testTopicQueues1";
        channel.queueDeclare(testTopicQueues1, true, false, false, null);
        String testTopicQueues2 = "testTopicQueues2";
        channel.queueDeclare(testTopicQueues2, true, false, false, null);

        channel.queueBind(testTopicQueues1, testTopic, "#.error");
        channel.queueBind(testTopicQueues1, testTopic, "order.*");

        channel.queueBind(testTopicQueues2, testTopic, "*.*");

        for (int i = 0; i < 20; i++) {
            String body = "日志信息：我第" + (i + 1) + "次访问了~~~~~";
            channel.basicPublish(testTopic, "goods.info", null, body.getBytes());
        }

        channel.close();
        connection.close();
    }
}
