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
 * 品牌表
 */
@ApiModel(value = "plus-ldl-goods-domain-Brand")
@Table(name = "tb_brand")
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "品牌id")
    private Integer id;

    /**
     * 品牌名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value = "品牌名称")
    private String name;

    /**
     * 品牌图片地址
     */
    @Column(name = "image")
    @ApiModelProperty(value = "品牌图片地址")
    private String image;

    /**
     * 品牌的首字母
     */
    @Column(name = "letter")
    @ApiModelProperty(value = "品牌的首字母")
    private String letter;

    /**
     * 排序
     */
    @Column(name = "seq")
    @ApiModelProperty(value = "排序")
    private Integer seq;

    private static final long serialVersionUID = 1L;

    /**
     * 获取品牌id
     *
     * @return id - 品牌id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置品牌id
     *
     * @param id 品牌id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取品牌名称
     *
     * @return name - 品牌名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品牌名称
     *
     * @param name 品牌名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取品牌图片地址
     *
     * @return image - 品牌图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置品牌图片地址
     *
     * @param image 品牌图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取品牌的首字母
     *
     * @return letter - 品牌的首字母
     */
    public String getLetter() {
        return letter;
    }

    /**
     * 设置品牌的首字母
     *
     * @param letter 品牌的首字母
     */
    public void setLetter(String letter) {
        this.letter = letter;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", image=" + image +
                ", letter=" + letter +
                ", seq=" + seq +
                "]";
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
        Brand other = (Brand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
                && (this.getLetter() == null ? other.getLetter() == null : this.getLetter().equals(other.getLetter()))
                && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getLetter() == null) ? 0 : getLetter().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        return result;
    }
}
