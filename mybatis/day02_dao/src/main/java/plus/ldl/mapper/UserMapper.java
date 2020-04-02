package plus.ldl.mapper;

import plus.ldl.domain.User;

import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月22日  9:35
 */
public interface UserMapper {
    User findById(int id);

    void insert(User user);

    List<User> findByCondition(User user);

    List<User> findByArray(int[] ids);

    List<User> findByUser(User user);

    List<User> findAll();

}
