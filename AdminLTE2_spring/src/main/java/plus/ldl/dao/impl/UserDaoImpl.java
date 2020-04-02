package plus.ldl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plus.ldl.dao.UserDao;
import plus.ldl.entity.Role;
import plus.ldl.entity.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  19:10
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return null;
    }

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return template.query(
                "select * from sys_user limit ?, ?",
                new BeanPropertyRowMapper<>(User.class),
                offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @Override
    public Long insert(User user) {
        template.update(
                "insert into sys_user(username, email, password, phoneNum) VALUES (?,?,?,?)",
                user.getUsername(), user.getEmail(), user.getPassword(), user.getPhonenum()
        );
        try {
            return template.queryForObject("select * from sys_user where username = ?",
                    new BeanPropertyRowMapper<>(User.class), user.getUsername()).getId();
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @Override
    public int update(User user) {
        return 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        int i = template.update("delete from sys_user_role where userId = ?", id);
        i += template.update("delete from sys_user WHERE id = ?", id);
        return i;
    }

    @Override
    public List<Role> queryRoleById(Long id) {
        return template.query(
                "select r.*\n" +
                        "from (select roleId from sys_user_role where userId = ?) ru\n" +
                        "         LEFT JOIN sys_role r on ru.roleId = r.id",
                new BeanPropertyRowMapper<>(Role.class), id);
    }

    @Override
    public void insertUserRoleRel(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds) {
            template.update(
                    "insert into sys_user_role(userId, roleId) VALUES (?,?)",
                    userId, roleId
            );
        }
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        try {
            return template.queryForObject("select * from sys_user where username = ? and password = ?",
                    new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
