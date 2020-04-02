package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author ldl.plus
 * @date 2020年02月27日  16:28
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserExist(String username) {
        try {
            return template.queryForObject(
                    "select * from travel.tab_user where username = ?",
                    new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean saveUser(User user) {
        int i = template.update(
                "insert into travel.tab_user(username, password, name," +
                        " birthday, sex, telephone, email, status, code)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(),
                user.getEmail(), user.getStatus(), user.getCode());
        return i == 1;
    }

    @Override
    public User findByCode(String code) {
        try {
            return template.queryForObject(
                    "select * from travel.tab_user where code = ?",
                    new BeanPropertyRowMapper<>(User.class), code);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateStatus(User user) {
        template.update(
                "update travel.tab_user SET status = 'Y' where username = ?",
                user.getUsername());

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            return template.queryForObject(
                    "select * from travel.tab_user where username = ? and password = ?",
                    new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateActivationCode(User user) {
        template.update("update travel.tab_user SET code = ? where uid = ?",
                user.getCode(), user.getUid());
    }
}
