package plus.ldl.service;

import plus.ldl.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月26日  23:04
 */
public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    void updateUser(User user);
}
