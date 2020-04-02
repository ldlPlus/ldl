package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ldl
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;


    /**
     * @param outMan 转出账户名
     * @param inMan  转入账户名
     * @param money  转账金额
     */
    @Override
    public void transfer(String outMan, String inMan, double money) {
        accountDao.out(outMan, money); // 扣钱
        // int i = 1 / 0;
        accountDao.in(inMan, money); // 加钱
    }

    @Override
    public void find() {

    }

    @Override
    public void update() {

    }
}
