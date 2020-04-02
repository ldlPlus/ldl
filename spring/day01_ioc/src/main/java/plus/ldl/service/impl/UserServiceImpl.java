package plus.ldl.service.impl;

import plus.ldl.dao.UserDao;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月08日  14:39
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
