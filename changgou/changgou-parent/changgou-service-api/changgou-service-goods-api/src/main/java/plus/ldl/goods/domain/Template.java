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
@ApiModel(value="plus-ldl-goods-domain-Template")
@Table(name = "tb_template")
public class Template implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="ID")
    private Integer id;

    /**
     * 模板名称
     */
    @Column(name = "`name`")
    @ApiModelProperty(value="模板名称")
    private String name;

    /**
     * 规格数量
     */
    @Column(name = "spec_num")
    @ApiModelProperty(value="规格数量")
    private Integer specNum;

    /**
     * 参数数量
     */
    @Column(name = "para_num")
    @ApiModelProperty(value="参数数量")
    private Integer paraNum;

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
     * 获取模板名称
     *
     * @return name - 模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模板名称
     *
     * @param name 模板名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取规格数量
     *
     * @return spec_num - 规格数量
     */
    public Integer getSpecNum() {
        return specNum;
    }

    /**
     * 设置规格数量
     *
     * @param specNum 规格数量
     */
    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    /**
     * 获取参数数量
     *
     * @return para_num - 参数数量
     */
    public Integer getParaNum() {
        return paraNum;
    }

    /**
     * 设置参数数量
     *
     * @param paraNum 参数数量
     */
    public void setParaNum(Integer paraNum) {
        this.paraNum = paraNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", specNum=").append(specNum);
        sb.append(", paraNum=").append(paraNum);
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
        Template other = (Template) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSpecNum() == null ? other.getSpecNum() == null : this.getSpecNum().equals(other.getSpecNum()))
            && (this.getParaNum() == null ? other.getParaNum() == null : this.getParaNum().equals(other.getParaNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSpecNum() == null) ? 0 : getSpecNum().hashCode());
        result = prime * result + ((getParaNum() == null) ? 0 : getParaNum().hashCode());
        return result;
    }
}