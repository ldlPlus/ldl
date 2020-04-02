package plus.ldl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.ldl.domain.User;
import plus.ldl.mapper.UserMapper;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootFirstApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     *
     */
    @Test
    public void testQuery() throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("user = " + user);
        List<User> users = userMapper.select(user);
        System.out.println("users = " + users);
    }

}
