package com.itheima.provider.dao;

import org.springframework.stereotype.Repository;
import plus.ldl.domain.Goods;

/**
 * 商品Dao
 */

@Repository
public class GoodsDao {


    public Goods findOne(int id) {
        return new Goods(1, "华为手机", 3999, 10000);
    }
}
