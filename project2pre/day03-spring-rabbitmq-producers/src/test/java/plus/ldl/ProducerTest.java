package plus.ldl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ldl.plus
 * @date 2020年04月30日  16:51
 * 测试生产者
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * hello world
     */
    @Test
    public void test26() throws Exception {
        rabbitTemplate.convertAndSend("spring_queue", "hello world spring ...");
    }

    /**
     * fanout
     */
    @Test
    public void test34() throws Exception {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout ...");
    }

    /**
     * topic
     */
    @Test
    public void test42() throws Exception {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "heima.hehe", "spring topic ...");
    }

    /**
     * direct
     */
    @Test
    public void test50() throws Exception {
        rabbitTemplate.convertAndSend("spring_direct_exchange", "waring", "spring direct ...");
    }
}
