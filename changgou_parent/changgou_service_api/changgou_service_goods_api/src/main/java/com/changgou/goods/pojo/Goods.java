package com.changgou.goods.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年06月05日  16:49
 * 商品信息组合对象
 */
public class Goods implements Serializable {

    private List<Sku> skus;

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    private Spu spu;
}
