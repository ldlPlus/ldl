package plus.ldl.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import plus.ldl.domain.Orders;
import plus.ldl.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月23日  10:24
 */
public class OrderMapperTest {
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
        List<Orders> orders = orderMapper.findAll();
        for (Orders order : orders) {
            System.out.println("order = " + order);
        }

    }

    /**
     *
     */
    @Test
    public void test57() throws Exception {
        List<User> users = userMapper.findAllAndOrders();
        for (User user : users) {
            System.out.println(user);
        }
    }

}