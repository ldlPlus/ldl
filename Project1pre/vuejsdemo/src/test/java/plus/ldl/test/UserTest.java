package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.ldl.domain.User;
import plus.ldl.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月26日  23:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    /**
     *
     */
    @Test
    public void testFindAll() throws Exception {
        List<User> users = userService.findAll();
        System.out.println("users = " + users);
    }


    /**
     *
     */
    @Test
    public void testFindOne() throws Exception {
        User user = userService.findById(1);
        System.out.println("user = " + user);
    }


    /**
     *
     */
    @Test
    public void testUpdate() throws Exception {
        User user = userService.findById(1);
        System.out.println("修改前user = " + user);

        user.setAge(22);
        user.setUsername("阿凡达");

        userService.updateUser(user);

        user = userService.findById(1);
        System.out.println("修改后user = " + user);
    }
}
