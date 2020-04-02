package com.itheima.prictice.dao.impl;

import com.itheima.prictice.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void find(int id) {
        System.out.println("find running:" + id);
    }

    @Override
    public void save() {
        System.out.println("save running....");
    }

    @Override
    public void update() {
        System.out.println("update running....");
    }

    @Override
    public void delete(int id) {
        System.out.println("delete running：" + id);
    }
}
