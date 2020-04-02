package plus.ldl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.domain.User;
import plus.ldl.mapper.UserMapper;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月29日  13:53
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
