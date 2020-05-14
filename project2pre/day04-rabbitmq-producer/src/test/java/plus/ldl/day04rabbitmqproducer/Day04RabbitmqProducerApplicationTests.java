package plus.ldl.day04rabbitmqproducer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day04RabbitmqProducerApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    /**
     * 设置回调
     */
    @Test
    public void test17() throws Exception {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("Day04RabbitmqProducerApplicationTests.test17执行了");
            if (ack) {
                System.out.println("接收成功消息 = " + cause);
            } else {
                System.out.println("接收失败消息 = " + cause);
            }
        });

        //设置交换机处理失败消息的模式
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("Day04RabbitmqProducerApplicationTests.test17");
            System.out.println("message = " + message);
            System.out.println("replyCode = " + replyCode);
            System.out.println("replyText = " + replyText);
            System.out.println("exchange = " + exchange);
            System.out.println("routingKey = " + routingKey);
        });

        rabbitTemplate.convertAndSend("topic_exchange", "confirm", "message confirm ...");
    }

    /**
     *
     */
    @Test
    public void test48() throws Exception {
        rabbitTemplate.convertAndSend("order_exchange", "order.msg ", "订单信息 。。。。。。。。。。。。");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
        }
    }

    /**
     * ttl
     */
    @Test
    public void test62() throws Exception {
        rabbitTemplate.convertAndSend("order_exchange", "order.msg ", "订单信息 。。。", message -> {
            message.getMessageProperties().setExpiration("5000");
            return message;
        });
    }


    /**
     * 死信队列
     */
    @Test
    public void test74() throws Exception {
        rabbitTemplate.convertAndSend("order_exchange", "order.dead.dead", "我会死吗？会成为死信吗？？？");
    }
}
