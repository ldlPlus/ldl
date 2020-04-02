package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.ldl.entity.Account;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月12日  10:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class JdbcTest {
    @Autowired
    private JdbcTemplate template;

    /**
     *
     */
    @Test
    public void test24() throws Exception {
        List<Account> accounts = template.query("select * from db4.account", new BeanPropertyRowMapper<>(Account.class));
        for (Account account : accounts) {
            System.out.println("account = " + account.getName() + "--" + account.getBalance());
        }
    }

}
