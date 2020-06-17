package com.changgou.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ldl.plus
 * @date 2020年06月16日  11:43
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Override
    public void add(Integer num, Long id, String username) {
        // 当添加数量<=0移除商品
        if (num <= 0) {
            redisTemplate.boundHashOps("Cart" + username).delete(id);
            Long size = redisTemplate.boundHashOps("Cart" + username).size();
            if (size == null || size <= 0) {
                redisTemplate.delete("Cart" + username);
            }
        }

        Result<Sku> skuResult = skuFeign.findById(id);
        Sku sku = skuResult.getData();
        Result<Spu> spuResult = spuFeign.findSpuById(sku.getSpuId());
        Spu spu = spuResult.getData();

        OrderItem orderItem = createOrderItem(num, sku, spu);

        redisTemplate.boundHashOps("Cart" + username).put(id + "", JSON.toJSONString(orderItem));
    }

    @Override
    public List<OrderItem> list(String username) {


        List<Object> list = redisTemplate.boundHashOps("Cart" + username).values();
        assert list != null;
        return list.stream().map(orderItem -> JSON.parseObject((String) orderItem, OrderItem.class)).collect(Collectors.toList());
    }

    private OrderItem createOrderItem(Integer num, Sku sku, Spu spu) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(orderItem.getPrice() * num);
        orderItem.setImage(spu.getImage());
        return orderItem;
    }
}
