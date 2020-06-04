package plus.ldl.goods.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * 商品类目
 */
@ApiModel(value = "plus-ldl-goods-domain-Category")
@Table(name = "tb_category")
public class Category implements Serializable {
    /**
     * 分类ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "分类ID")
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 商品数量
     */
    @Column(name = "goods_num")
    @ApiModelProperty(value = "商品数量")
    private Integer goodsNum;

    /**
     * 是否显示
     */
    @Column(name = "is_show")
    @ApiModelProperty(value = "是否显示")
    private String isShow;

    /**
     * 是否导航
     */
    @Column(name = "is_menu")
    @ApiModelProperty(value = "是否导航")
    private String isMenu;

    /**
     * 排序
     */
    @Column(name = "seq")
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 上级ID
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "上级ID")
    private Integer parentId;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    @ApiModelProperty(value = "模板ID")
    private Integer templateId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取分类ID
     *
     * @return id - 分类ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置分类ID
     *
     * @param id 分类ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品数量
     *
     * @return goods_num - 商品数量
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置商品数量
     *
     * @param goodsNum 商品数量
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 获取是否显示
     *
     * @return is_show - 是否显示
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     *
     * @param isShow 是否显示
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取是否导航
     *
     * @return is_menu - 是否导航
     */
    public String getIsMenu() {
        return isMenu;
    }

    /**
     * 设置是否导航
     *
     * @param isMenu 是否导航
     */
    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取上级ID
     *
     * @return parent_id - 上级ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级ID
     *
     * @param parentId 上级ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", isShow=").append(isShow);
        sb.append(", isMenu=").append(isMenu);
        sb.append(", seq=").append(seq);
        sb.append(", parentId=").append(parentId);
        sb.append(", templateId=").append(templateId);
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
        Category other = (Category) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getGoodsNum() == null ? other.getGoodsNum() == null : this.getGoodsNum().equals(other.getGoodsNum()))
                && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
                && (this.getIsMenu() == null ? other.getIsMenu() == null : this.getIsMenu().equals(other.getIsMenu()))
                && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
                && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
                && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGoodsNum() == null) ? 0 : getGoodsNum().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getIsMenu() == null) ? 0 : getIsMenu().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        return result;
    }
}
