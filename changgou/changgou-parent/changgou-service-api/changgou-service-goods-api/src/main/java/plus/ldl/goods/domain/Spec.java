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
@ApiModel(value="plus-ldl-goods-domain-Spec")
@Table(name = "tb_spec")
public class Spec implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="ID")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 规格选项
     */
    @Column(name = "`options`")
    @ApiModelProperty(value="规格选项")
    private String options;

    /**
     * 排序
     */
    @Column(name = "seq")
    @ApiModelProperty(value="排序")
    private Integer seq;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    @ApiModelProperty(value="模板ID")
    private Integer templateId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取规格选项
     *
     * @return options - 规格选项
     */
    public String getOptions() {
        return options;
    }

    /**
     * 设置规格选项
     *
     * @param options 规格选项
     */
    public void setOptions(String options) {
        this.options = options;
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
        sb.append(", options=").append(options);
        sb.append(", seq=").append(seq);
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
        Spec other = (Spec) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOptions() == null ? other.getOptions() == null : this.getOptions().equals(other.getOptions()))
            && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOptions() == null) ? 0 : getOptions().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        return result;
    }
}