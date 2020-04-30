package plus.ldl.day03springbootproducers;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.day03springbootproducers.rabbitmq.config.RabbitMQConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class Day03SpringbootProducersApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ExecutorService executorService = Executors.newFixedThreadPool(32);

    @Test
    void contextLoads() {
    }

    /**
     *
     */
    @Test
    public void test22() throws Exception {
        for (int i = 0; i < 32; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 1000000; j++) {
                    rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "boot.hello",
                            Thread.currentThread().getName() + "  -  " + j +
                                    "    boot  rabbitmq hello ...");
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // System.out.println("Day03SpringbootProducersApplicationTests.test22");
        }
    }

}
