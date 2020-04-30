package plus.ldl.day02redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Day02RedisApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.boundValueOps("name").set("zhangsan");
    }

    /**
     *
     */
    @Test
    public void test23() throws Exception {
        String name = redisTemplate.boundValueOps("name").get();
        System.out.println("name = " + name);
    }

}
