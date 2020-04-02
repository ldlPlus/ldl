package plus.ldl.mapper;

import plus.ldl.domain.User;

import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月23日  11:39
 */
public interface UserMapper {
    List<User> findAllAndOrders();
}
