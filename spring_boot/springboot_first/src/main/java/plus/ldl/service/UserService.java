package plus.ldl.service;

import plus.ldl.domain.User;

/**
 * @author ldl.plus
 * @date 2020年03月29日  13:52
 */
public interface UserService {
    User findById(Long id);

    void insertUser(User user);
}
