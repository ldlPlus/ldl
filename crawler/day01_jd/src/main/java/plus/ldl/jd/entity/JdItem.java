package plus.ldl.jd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 京东商品表(JdItem)实体类
 *
 * @author ldl.plus
 * @since 2020-03-15 12:35:33
 */
@Entity
@Table(name = "jd_item")
public class JdItem implements Serializable {
    private static final long serialVersionUID = 746056997185585333L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 商品集合ID
     */
    private Long spu;
    /**
     * 商品最小品类单元ID
     */
    private Long sku;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格
     */
    private Long price;
    /**
     * 商品图片
     */
    private String pic;
    /**
     * 商品详情地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpu() {
        return spu;
    }

    public void setSpu(Long spu) {
        this.spu = spu;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}