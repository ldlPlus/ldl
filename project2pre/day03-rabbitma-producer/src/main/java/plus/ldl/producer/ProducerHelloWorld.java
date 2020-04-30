package plus.ldl.producer;

import com.rabbitmq.client.AMQP;
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
public class ProducerHelloWorld {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.31.247");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        AMQP.Queue.DeclareOk helloWorld = channel.queueDeclare("hello_world", true, false, false, null);

        for (int i = 0; i < 10; i++) {
            String body = "我第" + (i + 1) + "次访问了~~~~~";
            channel.basicPublish("", "hello_world", null, body.getBytes());
        }

        channel.close();
        connection.close();
    }

}
