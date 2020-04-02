package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author ldl.plus
 * @date 2020年02月27日  16:27
 */
public interface UserDao {
    User findUserExist(String username);

    boolean saveUser(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);

    void updateActivationCode(User user);
}
