package plus.ldl.ssm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import plus.ldl.ssm.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月21日  12:35
 */
public interface UserService extends UserDetailsService {

    List<User> findAll() throws Exception;

    User findById(Integer id) throws Exception;

    User findUserByIdAndAllRole(Integer id) throws Exception;

    void save(User user) throws Exception;
}
