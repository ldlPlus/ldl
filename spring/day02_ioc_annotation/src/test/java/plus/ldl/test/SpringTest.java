package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.ldl.config.SpringConfiguration;
import plus.ldl.dao.UserDao;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月11日  9:32
 * spring整合Junit测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringTest {
    @Autowired
    private UserService userService;

    @Autowired
    public UserDao userDao;

    /**
     *
     */
    @Test
    public void test16() throws Exception {
        System.out.println("userService = " + userService);
    }

    /**
     *
     */
    @Test
    public void test38() throws Exception {
        System.out.println("userDao = " + userDao);

    }

    public static int f(int value) {

        try {

            return value * value;
        } finally {
            if (value == 2) {
                System.out.println("value = " + value);
                return 0;
            }
        }
    }

    /**
     *
     */
    @Test
    public void test61() throws Exception {
        int f = f(3);
        System.out.println("f = " + f);
    }
}
