package plus.ldl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.dao.UserDao;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月08日  14:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save() {
        userDao.save();
    }
}
