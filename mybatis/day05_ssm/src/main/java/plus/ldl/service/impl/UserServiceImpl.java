package plus.ldl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.domain.User;
import plus.ldl.mapper.UserMapper;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月24日  16:36
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.find2Login(user);
    }
}
