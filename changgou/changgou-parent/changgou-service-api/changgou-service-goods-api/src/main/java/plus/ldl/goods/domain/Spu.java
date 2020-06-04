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
@ApiModel(value="plus-ldl-goods-domain-Spu")
@Table(name = "tb_spu")
public class Spu implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 货号
     */
    @Column(name = "sn")
    @ApiModelProperty(value="货号")
    private String sn;

    /**
     * SPU名
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="SPU名")
    private String name;

    /**
     * 副标题
     */
    @Column(name = "caption")
    @ApiModelProperty(value="副标题")
    private String caption;

    /**
     * 品牌ID
     */
    @Column(name = "brand_id")
    @ApiModelProperty(value="品牌ID")
    private Integer brandId;

    /**
     * 一级分类
     */
    @Column(name = "category1_id")
    @ApiModelProperty(value="一级分类")
    private Integer category1Id;

    /**
     * 二级分类
     */
    @Column(name = "category2_id")
    @ApiModelProperty(value="二级分类")
    private Integer category2Id;

    /**
     * 三级分类
     */
    @Column(name = "category3_id")
    @ApiModelProperty(value="三级分类")
    private Integer category3Id;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    @ApiModelProperty(value="模板ID")
    private Integer templateId;

    /**
     * 运费模板id
     */
    @Column(name = "freight_id")
    @ApiModelProperty(value="运费模板id")
    private Integer freightId;

    /**
     * 图片
     */
    @Column(name = "image")
    @ApiModelProperty(value="图片")
    private String image;

    /**
     * 图片列表
     */
    @Column(name = "images")
    @ApiModelProperty(value="图片列表")
    private String images;

    /**
     * 售后服务
     */
    @Column(name = "sale_service")
    @ApiModelProperty(value="售后服务")
    private String saleService;

    /**
     * 介绍
     */
    @Column(name = "introduction")
    @ApiModelProperty(value="介绍")
    private String introduction;

    /**
     * 规格列表
     */
    @Column(name = "spec_items")
    @ApiModelProperty(value="规格列表")
    private String specItems;

    /**
     * 参数列表
     */
    @Column(name = "para_items")
    @ApiModelProperty(value="参数列表")
    private String paraItems;

    /**
     * 销量
     */
    @Column(name = "sale_num")
    @ApiModelProperty(value="销量")
    private Integer saleNum;

    /**
     * 评论数
     */
    @Column(name = "comment_num")
    @ApiModelProperty(value="评论数")
    private Integer commentNum;

    /**
     * 是否上架
     */
    @Column(name = "is_marketable")
    @ApiModelProperty(value="是否上架")
    private String isMarketable;

    /**
     * 是否启用规格
     */
    @Column(name = "is_enable_spec")
    @ApiModelProperty(value="是否启用规格")
    private String isEnableSpec;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    @ApiModelProperty(value="是否删除")
    private String isDelete;

    /**
     * 审核状态
     */
    @Column(name = "`status`")
    @ApiModelProperty(value="审核状态")
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取货号
     *
     * @return sn - 货号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置货号
     *
     * @param sn 货号
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取SPU名
     *
     * @return name - SPU名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置SPU名
     *
     * @param name SPU名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取副标题
     *
     * @return caption - 副标题
     */
    public String getCaption() {
        return caption;
    }

    /**
     * 设置副标题
     *
     * @param caption 副标题
     */
    public void setCaption(String caption) {
        this.caption = caption;
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

    /**
     * 获取一级分类
     *
     * @return category1_id - 一级分类
     */
    public Integer getCategory1Id() {
        return category1Id;
    }

    /**
     * 设置一级分类
     *
     * @param category1Id 一级分类
     */
    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    /**
     * 获取二级分类
     *
     * @return category2_id - 二级分类
     */
    public Integer getCategory2Id() {
        return category2Id;
    }

    /**
     * 设置二级分类
     *
     * @param category2Id 二级分类
     */
    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    /**
     * 获取三级分类
     *
     * @return category3_id - 三级分类
     */
    public Integer getCategory3Id() {
        return category3Id;
    }

    /**
     * 设置三级分类
     *
     * @param category3Id 三级分类
     */
    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    /**
     * 获取模板ID
     *
     * @return template_id - 模板ID
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板ID
     *
     * @param templateId 模板ID
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取运费模板id
     *
     * @return freight_id - 运费模板id
     */
    public Integer getFreightId() {
        return freightId;
    }

    /**
     * 设置运费模板id
     *
     * @param freightId 运费模板id
     */
    public void setFreightId(Integer freightId) {
        this.freightId = freightId;
    }

    /**
     * 获取图片
     *
     * @return image - 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取图片列表
     *
     * @return images - 图片列表
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置图片列表
     *
     * @param images 图片列表
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * 获取售后服务
     *
     * @return sale_service - 售后服务
     */
    public String getSaleService() {
        return saleService;
    }

    /**
     * 设置售后服务
     *
     * @param saleService 售后服务
     */
    public void setSaleService(String saleService) {
        this.saleService = saleService;
    }

    /**
     * 获取介绍
     *
     * @return introduction - 介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置介绍
     *
     * @param introduction 介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取规格列表
     *
     * @return spec_items - 规格列表
     */
    public String getSpecItems() {
        return specItems;
    }

    /**
     * 设置规格列表
     *
     * @param specItems 规格列表
     */
    public void setSpecItems(String specItems) {
        this.specItems = specItems;
    }

    /**
     * 获取参数列表
     *
     * @return para_items - 参数列表
     */
    public String getParaItems() {
        return paraItems;
    }

    /**
     * 设置参数列表
     *
     * @param paraItems 参数列表
     */
    public void setParaItems(String paraItems) {
        this.paraItems = paraItems;
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
     * 获取是否上架
     *
     * @return is_marketable - 是否上架
     */
    public String getIsMarketable() {
        return isMarketable;
    }

    /**
     * 设置是否上架
     *
     * @param isMarketable 是否上架
     */
    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable;
    }

    /**
     * 获取是否启用规格
     *
     * @return is_enable_spec - 是否启用规格
     */
    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    /**
     * 设置是否启用规格
     *
     * @param isEnableSpec 是否启用规格
     */
    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取审核状态
     *
     * @return status - 审核状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置审核状态
     *
     * @param status 审核状态
     */
    public void setStatus(String status) {
        this.status = status;
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
        sb.append(", caption=").append(caption);
        sb.append(", brandId=").append(brandId);
        sb.append(", category1Id=").append(category1Id);
        sb.append(", category2Id=").append(category2Id);
        sb.append(", category3Id=").append(category3Id);
        sb.append(", templateId=").append(templateId);
        sb.append(", freightId=").append(freightId);
        sb.append(", image=").append(image);
        sb.append(", images=").append(images);
        sb.append(", saleService=").append(saleService);
        sb.append(", introduction=").append(introduction);
        sb.append(", specItems=").append(specItems);
        sb.append(", paraItems=").append(paraItems);
        sb.append(", saleNum=").append(saleNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", isMarketable=").append(isMarketable);
        sb.append(", isEnableSpec=").append(isEnableSpec);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", status=").append(status);
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
        Spu other = (Spu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSn() == null ? other.getSn() == null : this.getSn().equals(other.getSn()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCaption() == null ? other.getCaption() == null : this.getCaption().equals(other.getCaption()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getCategory1Id() == null ? other.getCategory1Id() == null : this.getCategory1Id().equals(other.getCategory1Id()))
            && (this.getCategory2Id() == null ? other.getCategory2Id() == null : this.getCategory2Id().equals(other.getCategory2Id()))
            && (this.getCategory3Id() == null ? other.getCategory3Id() == null : this.getCategory3Id().equals(other.getCategory3Id()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getFreightId() == null ? other.getFreightId() == null : this.getFreightId().equals(other.getFreightId()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getImages() == null ? other.getImages() == null : this.getImages().equals(other.getImages()))
            && (this.getSaleService() == null ? other.getSaleService() == null : this.getSaleService().equals(other.getSaleService()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getSpecItems() == null ? other.getSpecItems() == null : this.getSpecItems().equals(other.getSpecItems()))
            && (this.getParaItems() == null ? other.getParaItems() == null : this.getParaItems().equals(other.getParaItems()))
            && (this.getSaleNum() == null ? other.getSaleNum() == null : this.getSaleNum().equals(other.getSaleNum()))
            && (this.getCommentNum() == null ? other.getCommentNum() == null : this.getCommentNum().equals(other.getCommentNum()))
            && (this.getIsMarketable() == null ? other.getIsMarketable() == null : this.getIsMarketable().equals(other.getIsMarketable()))
            && (this.getIsEnableSpec() == null ? other.getIsEnableSpec() == null : this.getIsEnableSpec().equals(other.getIsEnableSpec()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSn() == null) ? 0 : getSn().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCaption() == null) ? 0 : getCaption().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getCategory1Id() == null) ? 0 : getCategory1Id().hashCode());
        result = prime * result + ((getCategory2Id() == null) ? 0 : getCategory2Id().hashCode());
        result = prime * result + ((getCategory3Id() == null) ? 0 : getCategory3Id().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getFreightId() == null) ? 0 : getFreightId().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getImages() == null) ? 0 : getImages().hashCode());
        result = prime * result + ((getSaleService() == null) ? 0 : getSaleService().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getSpecItems() == null) ? 0 : getSpecItems().hashCode());
        result = prime * result + ((getParaItems() == null) ? 0 : getParaItems().hashCode());
        result = prime * result + ((getSaleNum() == null) ? 0 : getSaleNum().hashCode());
        result = prime * result + ((getCommentNum() == null) ? 0 : getCommentNum().hashCode());
        result = prime * result + ((getIsMarketable() == null) ? 0 : getIsMarketable().hashCode());
        result = prime * result + ((getIsEnableSpec() == null) ? 0 : getIsEnableSpec().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}