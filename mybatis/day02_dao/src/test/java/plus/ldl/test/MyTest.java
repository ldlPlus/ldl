package plus.ldl.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import plus.ldl.domain.User;
import plus.ldl.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


/**
 * @author plus.ldl.plus
 * @date 2020年03月22日  9:39
 */
public class MyTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sessionFactory.openSession();

        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     *
     */
    @Test
    public void test14() throws Exception {

        User user = userMapper.findById(1);
        System.out.println("user = " + user);

    }

    /**
     *
     */
    @Test
    public void test53() throws Exception {
        User user = new User();
        user.setUsername("dsf");
        user.setPassword("fdAsf");

        userMapper.insert(user);

        System.out.println("user = " + user);
    }

    /**
     *
     */
    @Test
    public void test67() throws Exception {
        User user = new User();
        int id = user.getId();
        System.out.println("id = " + id);
    }

    /**
     *
     */
    @Test
    public void test77() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("");
        user.setPassword("");

        List<User> users = userMapper.findByCondition(user);
        System.out.println("users = " + users);
    }

    /**
     *
     */
    @Test
    public void test92() throws Exception {
        int[] ids = {1};
        List<User> users = userMapper.findByArray(ids);
        System.out.println("users = " + users);
    }

    /**
     *
     */
    @Test
    public void test102() throws Exception {
        User user = new User();
        user.setIds(new int[]{1, 2, 3});
        user.setId(0);
        user.setUsername("");
        user.setPassword("1");

        List<User> users = userMapper.findByUser(user);
        System.out.println("users = " + users);
    }

    /**
     *
     */
    @Test
    public void test118() throws Exception {
        User user = new User();
        user.setUsername("23432");
        user.setPassword("23423432");
        user.setBirthday(new Date());

        userMapper.insert(user);

    }

    /**
     *
     */
    @Test
    public void test133() throws Exception {
        User user = userMapper.findById(15);
        System.out.println("user = " + user);
    }

    /**
     *
     */
    @Test
    public void test143() throws Exception {
        PageHelper.startPage(2, 3);
        List<User> users = userMapper.findAll();
        System.out.println("users = " + users);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println("pageInfo = " + pageInfo);
        for (User user : pageInfo.getList()) {
            System.out.println("user = " + user);
        }
    }
}
