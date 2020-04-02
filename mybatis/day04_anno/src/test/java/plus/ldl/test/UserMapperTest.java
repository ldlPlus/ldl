package plus.ldl.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import plus.ldl.domain.Orders;
import plus.ldl.domain.User;
import plus.ldl.mapper.OrderMapper;
import plus.ldl.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月23日  14:45
 */
public class UserMapperTest {
    SqlSession sqlSession;
    OrderMapper orderMapper;
    UserMapper userMapper;

    @Before
    public void init() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        orderMapper = sqlSession.getMapper(OrderMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     *
     */
    @Test
    public void test59() throws Exception {
        User user = new User();
        user.setUsername("usser");
        user.setPassword("wpefk");
        user.setBirthday(new Date());

        userMapper.insert(user);
        System.out.println("user = " + user);
    }

    /**
     *
     */
    @Test
    public void test74() throws Exception {
        List<Orders> orders = orderMapper.findAllAndUser();
        for (Orders order : orders) {
            System.out.println(order);
        }
    }

    /**
     *
     */
    @Test
    public void test86() throws Exception {
        List<User> users = userMapper.findAllAndOrders();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    /**
     *
     */
    @Test
    public void test98() throws Exception {
        List<User> users = userMapper.findAllWithRoles();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }
}