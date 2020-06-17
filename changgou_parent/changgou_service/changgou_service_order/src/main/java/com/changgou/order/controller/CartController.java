package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年06月16日  11:41
 * 购物车操作
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 加入购物车
     *
     * @param num 加入购物车数量
     * @param id  商品Id
     * @return
     */
    @GetMapping("/add")
    public Result add(Integer num, Long id) {
        cartService.add(num, id, "username");
        return new Result(true, StatusCode.OK, "添加购物车成功");
    }

    @GetMapping("/list")
    public Result<List<OrderItem>> list() {
        String username = "username";
        List<OrderItem> orderItemList = cartService.list(username);
        return new Result<>(true, StatusCode.OK, "购物车列表查询成功", orderItemList);
    }
}
