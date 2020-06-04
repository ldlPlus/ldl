package plus.ldl.goods.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;


/**
 * @author ldl.plus 
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@ApiModel(value="plus-ldl-goods-domain-CategoryBrand")
@Table(name = "tb_category_brand")
public class CategoryBrand implements Serializable {
    /**
     * 分类ID
     */
    @Column(name = "category_id")
    @ApiModelProperty(value="分类ID")
    private Integer categoryId;

    /**
     * 品牌ID
     */
    @Column(name = "brand_id")
    @ApiModelProperty(value="品牌ID")
    private Integer brandId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取分类ID
     *
     * @return category_id - 分类ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类ID
     *
     * @param categoryId 分类ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取品牌ID
     *
     * @return brand_id - 品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌ID
     *
     * @param brandId 品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CategoryBrand other = (CategoryBrand) that;
        return (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        return result;
    }
}