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
@ApiModel(value="plus-ldl-goods-domain-Album")
@Table(name = "tb_album")
public class Album implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 相册名称
     */
    @Column(name = "title")
    @ApiModelProperty(value="相册名称")
    private String title;

    /**
     * 相册封面
     */
    @Column(name = "image")
    @ApiModelProperty(value="相册封面")
    private String image;

    /**
     * 图片列表
     */
    @Column(name = "image_items")
    @ApiModelProperty(value="图片列表")
    private String imageItems;

    private static final long serialVersionUID = 1L;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取相册名称
     *
     * @return title - 相册名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置相册名称
     *
     * @param title 相册名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取相册封面
     *
     * @return image - 相册封面
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置相册封面
     *
     * @param image 相册封面
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取图片列表
     *
     * @return image_items - 图片列表
     */
    public String getImageItems() {
        return imageItems;
    }

    /**
     * 设置图片列表
     *
     * @param imageItems 图片列表
     */
    public void setImageItems(String imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", image=").append(image);
        sb.append(", imageItems=").append(imageItems);
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
        Album other = (Album) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getImageItems() == null ? other.getImageItems() == null : this.getImageItems().equals(other.getImageItems()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getImageItems() == null) ? 0 : getImageItems().hashCode());
        return result;
    }
}