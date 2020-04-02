package plus.ldl.springtest.dao;

import plus.ldl.springtest.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月12日  21:36
 */
public interface UserDao {
    List<User> findAll();
}
