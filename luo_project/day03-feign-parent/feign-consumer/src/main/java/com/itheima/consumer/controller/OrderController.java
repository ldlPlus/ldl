package com.itheima.consumer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.domain.Goods;
import plus.ldl.feign.GoodsInterface;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class OrderController {

    @Resource
    private GoodsInterface goodsInterface;

    @GetMapping("/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        Goods goods = goodsInterface.findOne(id);
        return goods;
    }


}
