package com.itheima.prictice.service.impl;

import com.itheima.prictice.dao.UserDao;
import com.itheima.prictice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ldl
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void find(int id) {
        userDao.find(id);
    }

    @Override
    public void save() {
        userDao.save();
    }

    @Override
    public void update() {
        userDao.update();
        int i = 1 / 0;
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
