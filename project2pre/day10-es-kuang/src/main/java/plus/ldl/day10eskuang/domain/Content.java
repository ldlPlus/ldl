package plus.ldl.day10eskuang.domain;

import java.util.Objects;

/**
 * @author ldl.plus
 * @date 2020年05月15日  19:02
 */
public class Content {

    private Integer id;
    private String title;
    private String img;
    private String price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) &&
                Objects.equals(title, content.title) &&
                Objects.equals(img, content.img) &&
                Objects.equals(price, content.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, img, price);
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Content(String title, String img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public Content(Integer id, String title, String img, String price) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public Content() {
    }

}
