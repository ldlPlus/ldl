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
public class ProducerRouting {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.31.247");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String testDirect = "testDirect";
        channel.exchangeDeclare(testDirect, BuiltinExchangeType.DIRECT, true, false, false, null);

        String testDirectQueues1 = "testDirectQueues1";
        channel.queueDeclare(testDirectQueues1, true, false, false, null);
        String testDirectQueues2 = "testDirectQueues2";
        channel.queueDeclare(testDirectQueues2, true, false, false, null);

        channel.queueBind(testDirectQueues1, testDirect, "error");

        channel.queueBind(testDirectQueues2, testDirect, "info");
        channel.queueBind(testDirectQueues2, testDirect, "error");
        channel.queueBind(testDirectQueues2, testDirect, "warning");

        for (int i = 0; i < 20; i++) {
            String body = "日志信息：我第" + (i + 1) + "次访问了~~~~~";
            channel.basicPublish(testDirect, "error", null, body.getBytes());
        }

        channel.close();
        connection.close();
    }
}
