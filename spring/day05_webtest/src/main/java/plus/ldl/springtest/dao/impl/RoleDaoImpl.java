package plus.ldl.springtest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plus.ldl.springtest.dao.RoleDao;
import plus.ldl.springtest.domain.Role;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  16:28
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private JdbcTemplate template;


    @Override
    public List<Role> findAll() {
        return template.query(
                "select * from test.sys_role",
                new BeanPropertyRowMapper<>(Role.class));
    }
}
