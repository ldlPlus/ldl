package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {


    @Qualifier("template")
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 扣钱方法
     *
     * @param outMan 转出账户的名称
     * @param money  转出金额
     */
    @Override
    public void out(String outMan, double money) {
        String sql = "update traveltest.account set money = money - ? where name = ?";
        jdbcTemplate.update(sql, money, outMan);
    }

    /**
     * 加钱的方法
     *
     * @param inMan 转入账户的名称
     * @param money 转入金额
     */
    @Override
    public void in(String inMan, double money) {
        String sql = "update traveltest.account set money = money + ? where name = ?";
        jdbcTemplate.update(sql, money, inMan);
    }
}
