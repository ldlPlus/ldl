package plus.ldl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.dao.UserDao;
import plus.ldl.domain.User;
import plus.ldl.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月26日  23:05
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
