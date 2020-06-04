package plus.ldl.mapperplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.mapperplusdemo.entity.User;
import plus.ldl.mapperplusdemo.mapper.UserMapper;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MapperplusdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     *
     */
    @Test
    public void test29() throws Exception {
        User user = new User();
        // user.setId(1L);
        user.setName("Rose");
        user.setAge(33);
        user.setEmail("93764");

        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }

    /**
     *
     */
    @Test
    public void test44() throws Exception {
        userMapper.insert(new User("月白烟青水暗流", 60, "dfbb@163.com"));
    }

    /**
     * 测试乐观锁
     */
    @Test
    public void test52() throws Exception {
        User user = userMapper.selectById(6);
        user.setAge(20000);
        userMapper.updateById(user);
    }

    /**
     *
     */
    @Test
    public void test62() throws Exception {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void test74() throws Exception {
        Page<User> userPage = new Page<>(1, 3);
        userMapper.selectPage(userPage, null);
        List<User> users = userPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     * 删除操作
     */
    @Test
    public void test88() throws Exception {
        int i = userMapper.deleteById(1L);
        System.out.println("i = " + i);
    }

    /**
     *
     */
    @Test
    public void test97() throws Exception {
        int i = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println("i = " + i);
    }

    /**
     * 逻辑删除，配置插件
     */
    @Test
    public void test109() throws Exception {
        int i = userMapper.deleteById(7L);
        System.out.println("i = " + i);
    }

    /**
     *
     */
    @Test
    public void test115() throws Exception {
        List<User> users = userMapper.findDeleted();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     *
     */
    @Test
    public void test126() throws Exception {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // ge大于、gt大于等于、le小于、lt小于等于
        userQueryWrapper.ge(true, "age", 30);
        userQueryWrapper.select("id", "name");
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     *
     */
    @Test
    public void test142() throws Exception {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    }

    /**
     *
     */
    @Test
    public void test151() throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {

        }

    }
}
