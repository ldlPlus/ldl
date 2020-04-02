package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author ldl.plus
 * @date 2020年02月27日  16:16
 */
public interface UserService {

    User userExist(String username);

    boolean saveUser(User user);

    User active(String code);

    User login(String username, String password);

    void resendEmail(User user);
}
