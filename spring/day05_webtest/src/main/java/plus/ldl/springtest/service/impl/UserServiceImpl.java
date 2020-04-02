package plus.ldl.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.springtest.dao.UserDao;
import plus.ldl.springtest.domain.User;
import plus.ldl.springtest.service.UserService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月16日  17:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
