package com.changgou.goods.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ldl.plus
 * @date 2020年06月06日  13:21
 */
@Table(name = "tb_category_brand")
public class CategoryBrand {

    @Id
    private Integer categoryId;
    @Id
    private Integer brandId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
