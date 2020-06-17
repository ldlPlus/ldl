package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年06月16日  11:43
 */
public interface CartService {

    /**
     * 加入购物车
     *
     * @param num
     * @param id
     * @param username
     */
    void add(Integer num, Long id, String username);

    /**
     * 购物车查询
     *
     * @param username
     * @return
     */
    List<OrderItem> list(String username);

}

