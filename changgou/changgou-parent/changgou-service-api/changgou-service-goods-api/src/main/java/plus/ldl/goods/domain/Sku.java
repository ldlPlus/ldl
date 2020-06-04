package plus.ldl.goods.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * 商品表
 */
@ApiModel(value = "plus-ldl-goods-domain-Sku")
@Table(name = "tb_sku")
public class Sku implements Serializable {
    /**
     * 商品id
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "商品id")
    private String id;

    /**
     * 商品条码
     */
    @Column(name = "sn")
    @ApiModelProperty(value = "商品条码")
    private String sn;

    /**
     * SKU名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value = "SKU名称")
    private String name;

    /**
     * 价格（分）
     */
    @Column(name = "price")
    @ApiModelProperty(value = "价格（分）")
    private Integer price;

    /**
     * 库存数量
     */
    @Column(name = "num")
    @ApiModelProperty(value = "库存数量")
    private Integer num;

    /**
     * 库存预警数量
     */
    @Column(name = "alert_num")
    @ApiModelProperty(value = "库存预警数量")
    private Integer alertNum;

    /**
     * 商品图片
     */
    @Column(name = "image")
    @ApiModelProperty(value = "商品图片")
    private String image;

    /**
     * 商品图片列表
     */
    @Column(name = "images")
    @ApiModelProperty(value = "商品图片列表")
    private String images;

    /**
     * 重量（克）
     */
    @Column(name = "weight")
    @ApiModelProperty(value = "重量（克）")
    private Integer weight;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * SPUID
     */
    @Column(name = "spu_id")
    @ApiModelProperty(value = "SPUID")
    private String spuId;

    /**
     * 类目ID
     */
    @Column(name = "category_id")
    @ApiModelProperty(value = "类目ID")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @Column(name = "category_name")
    @ApiModelProperty(value = "类目名称")
    private String categoryName;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    /**
     * 规格
     */
    @Column(name = "spec")
    @ApiModelProperty(value = "规格")
    private String spec;

    /**
     * 销量
     */
    @Column(name = "sale_num")
    @ApiModelProperty(value = "销量")
    private Integer saleNum;

    /**
     * 评论数
     */
    @Column(name = "comment_num")
    @ApiModelProperty(value = "评论数")
    private Integer commentNum;

    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    @Column(name = "`status`")
    @ApiModelProperty(value = "商品状态 1-正常，2-下架，3-删除")
    private String status;

    @Column(name = "version")
    @ApiModelProperty(value = "")
    private Integer version;

    private static final long serialVersionUID = 1L;

    /**
     * 获取商品id
     *
     * @return id - 商品id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品id
     *
     * @param id 商品id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品条码
     *
     * @return sn - 商品条码
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置商品条码
     *
     * @param sn 商品条码
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取SKU名称
     *
     * @return name - SKU名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置SKU名称
     *
     * @param name SKU名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取价格（分）
     *
     * @return price - 价格（分）
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置价格（分）
     *
     * @param price 价格（分）
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取库存数量
     *
     * @return num - 库存数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置库存数量
     *
     * @param num 库存数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取库存预警数量
     *
     * @return alert_num - 库存预警数量
     */
    public Integer getAlertNum() {
        return alertNum;
    }

    /**
     * 设置库存预警数量
     *
     * @param alertNum 库存预警数量
     */
    public void setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
    }

    /**
     * 获取商品图片
     *
     * @return image - 商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置商品图片
     *
     * @param image 商品图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取商品图片列表
     *
     * @return images - 商品图片列表
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置商品图片列表
     *
     * @param images 商品图片列表
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * 获取重量（克）
     *
     * @return weight - 重量（克）
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置重量（克）
     *
     * @param weight 重量（克）
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取SPUID
     *
     * @return spu_id - SPUID
     */
    public String getSpuId() {
        return spuId;
    }

    /**
     * 设置SPUID
     *
     * @param spuId SPUID
     */
    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取类目ID
     *
     * @return category_id - 类目ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类目ID
     *
     * @param categoryId 类目ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取类目名称
     *
     * @return category_name - 类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类目名称
     *
     * @param categoryName 类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取品牌名称
     *
     * @return brand_name - 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置品牌名称
     *
     * @param brandName 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取规格
     *
     * @return spec - 规格
     */
    public String getSpec() {
        return spec;
    }

    /**
     * 设置规格
     *
     * @param spec 规格
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 获取销量
     *
     * @return sale_num - 销量
     */
    public Integer getSaleNum() {
        return saleNum;
    }

    /**
     * 设置销量
     *
     * @param saleNum 销量
     */
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * 获取评论数
     *
     * @return comment_num - 评论数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置评论数
     *
     * @param commentNum 评论数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * 获取商品状态 1-正常，2-下架，3-删除
     *
     * @return status - 商品状态 1-正常，2-下架，3-删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置商品状态 1-正常，2-下架，3-删除
     *
     * @param status 商品状态 1-正常，2-下架，3-删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sn=").append(sn);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", alertNum=").append(alertNum);
        sb.append(", image=").append(image);
        sb.append(", images=").append(images);
        sb.append(", weight=").append(weight);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", spuId=").append(spuId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", brandName=").append(brandName);
        sb.append(", spec=").append(spec);
        sb.append(", saleNum=").append(saleNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
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
        Sku other = (Sku) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSn() == null ? other.getSn() == null : this.getSn().equals(other.getSn()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
                && (this.getAlertNum() == null ? other.getAlertNum() == null : this.getAlertNum().equals(other.getAlertNum()))
                && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
                && (this.getImages() == null ? other.getImages() == null : this.getImages().equals(other.getImages()))
                && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
                && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
                && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
                && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
                && (this.getSpec() == null ? other.getSpec() == null : this.getSpec().equals(other.getSpec()))
                && (this.getSaleNum() == null ? other.getSaleNum() == null : this.getSaleNum().equals(other.getSaleNum()))
                && (this.getCommentNum() == null ? other.getCommentNum() == null : this.getCommentNum().equals(other.getCommentNum()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSn() == null) ? 0 : getSn().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getAlertNum() == null) ? 0 : getAlertNum().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getImages() == null) ? 0 : getImages().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getSpec() == null) ? 0 : getSpec().hashCode());
        result = prime * result + ((getSaleNum() == null) ? 0 : getSaleNum().hashCode());
        result = prime * result + ((getCommentNum() == null) ? 0 : getCommentNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}
