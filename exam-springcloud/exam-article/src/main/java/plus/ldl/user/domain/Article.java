package plus.ldl.user.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ldl.plus
 * @date 2020年05月14日  18:27
 * $VAR1
 */

/**
 * 文章
 */
public class Article implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 作者编号
     */
    private Long authorid;

    /**
     * 发表日期
     */
    private Date createtime;

    /**
     * 审核状态
     */
    private String state;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Long authorid) {
        this.authorid = authorid;
    }

    public String getCreatetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createtime);
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                " id=" + id +
                ", title=" + title +
                ", content=" + content +
                ", authorid=" + authorid +
                ", createtime=" + createtime +
                ", state=" + state +
                "]";
    }
}