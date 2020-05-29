package plus.ldl.day01_springboot_helloworld;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RuntimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

// @SpringBootTest
class Day01SpringbootHelloworldApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        System.out.println("RuntimeUtil.getProcessorCount() = " + RuntimeUtil.getProcessorCount());
    }

    /**
     *
     */
    @Test
    public void test21() throws Exception {
        System.exit(0);
        Properties system = System.getProperties();
        String value = new ObjectMapper().writeValueAsString(system);
        System.out.println("System.getProperties() = " + system);
    }

    /**
     *
     */
    @Test
    public void test34() throws Exception {
        assert 1 == 2;
        System.out.println("Day01SpringbootHelloworldApplicationTests.test34");
    }

    /**
     *
     */
    @Test
    public void test42() throws Exception {
        String s = null;
        Assert.notNull(s);
        System.out.println("s = " + s);
    }

    /**
     *
     */
    @Test
    public void test53() throws Exception {
        Assertions.assertEquals("a", "a");
        Assertions.assertFalse(true);
        System.out.println("Day01SpringbootHelloworldApplicationTests.test53");
    }

}
