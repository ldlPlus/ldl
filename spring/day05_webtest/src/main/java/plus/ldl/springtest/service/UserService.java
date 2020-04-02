package plus.ldl.springtest.service;

import plus.ldl.springtest.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  17:33
 */
public interface UserService {
    List<User> findAll();
}
