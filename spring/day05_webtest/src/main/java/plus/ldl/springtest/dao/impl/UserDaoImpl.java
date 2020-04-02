package plus.ldl.springtest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plus.ldl.springtest.dao.UserDao;
import plus.ldl.springtest.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月12日  21:36
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("select * from test.sys_user",
                new BeanPropertyRowMapper<>(User.class));
    }
}
