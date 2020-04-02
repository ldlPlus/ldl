package com.itheima.dao;

public interface AccountDao {
    /**
     * 转出
     * @param outMan 转出账户
     * @param money 转出金额
     */
    public void out(String outMan, double money);

    /**
     * 转入
     * @param inMan 转入账户
     * @param money 转入金额
     */
    public void in(String inMan, double money);
}
