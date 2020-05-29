package plus.ldl.configprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author ldl.plus
 * @date 2020年05月25日  16:01
 */
@SpringBootTest
public class ProviderTest {

    @Autowired
    private Environment environment;

    /**
     *
     */
    @Test
    public void test17() throws Exception {
        String[] activeProfiles = environment.getActiveProfiles();
        String[] defaultProfiles = environment.getDefaultProfiles();
        System.out.println(Arrays.toString(activeProfiles));
        System.out.println(Arrays.toString(defaultProfiles));
    }
}
