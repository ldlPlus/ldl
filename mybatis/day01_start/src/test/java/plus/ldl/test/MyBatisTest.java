package plus.ldl.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import plus.ldl.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月20日  10:35
 */
public class MyBatisTest {
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sessionFactory.openSession();
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
    public void test1() throws Exception {
        List<User> selectList = sqlSession.selectList("plus.plus.ldl.mapper.UserMapper.findAll");
        System.out.println("selectList = " + selectList);

        User user = new User();
        user.setUsername("plus.ldl");
        user.setPassword("123");

        sqlSession.insert("plus.plus.ldl.mapper.UserMapper.insert", user);
    }

    /**
     *
     */
    @Test
    public void test55() throws Exception {
        int i = sqlSession.delete("plus.plus.ldl.mapper.UserMapper.deleteByPrimaryKey", 5);
        System.out.println("i = " + i);
    }
}
