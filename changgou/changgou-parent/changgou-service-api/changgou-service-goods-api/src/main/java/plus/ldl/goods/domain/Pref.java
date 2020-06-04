package plus.ldl.goods.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * @author ldl.plus 
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@ApiModel(value="plus-ldl-goods-domain-Pref")
@Table(name = "tb_pref")
public class Pref implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="ID")
    private Integer id;

    /**
     * 分类ID
     */
    @Column(name = "cate_id")
    @ApiModelProperty(value="分类ID")
    private Integer cateId;

    /**
     * 消费金额
     */
    @Column(name = "buy_money")
    @ApiModelProperty(value="消费金额")
    private Integer buyMoney;

    /**
     * 优惠金额
     */
    @Column(name = "pre_money")
    @ApiModelProperty(value="优惠金额")
    private Integer preMoney;

    /**
     * 活动开始日期
     */
    @Column(name = "start_time")
    @ApiModelProperty(value="活动开始日期")
    private Date startTime;

    /**
     * 活动截至日期
     */
    @Column(name = "end_time")
    @ApiModelProperty(value="活动截至日期")
    private Date endTime;

    /**
     * 类型
     */
    @Column(name = "`type`")
    @ApiModelProperty(value="类型")
    private String type;

    /**
     * 状态
     */
    @Column(name = "`state`")
    @ApiModelProperty(value="状态")
    private String state;

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
     * 获取分类ID
     *
     * @return cate_id - 分类ID
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * 设置分类ID
     *
     * @param cateId 分类ID
     */
    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    /**
     * 获取消费金额
     *
     * @return buy_money - 消费金额
     */
    public Integer getBuyMoney() {
        return buyMoney;
    }

    /**
     * 设置消费金额
     *
     * @param buyMoney 消费金额
     */
    public void setBuyMoney(Integer buyMoney) {
        this.buyMoney = buyMoney;
    }

    /**
     * 获取优惠金额
     *
     * @return pre_money - 优惠金额
     */
    public Integer getPreMoney() {
        return preMoney;
    }

    /**
     * 设置优惠金额
     *
     * @param preMoney 优惠金额
     */
    public void setPreMoney(Integer preMoney) {
        this.preMoney = preMoney;
    }

    /**
     * 获取活动开始日期
     *
     * @return start_time - 活动开始日期
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置活动开始日期
     *
     * @param startTime 活动开始日期
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取活动截至日期
     *
     * @return end_time - 活动截至日期
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置活动截至日期
     *
     * @param endTime 活动截至日期
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cateId=").append(cateId);
        sb.append(", buyMoney=").append(buyMoney);
        sb.append(", preMoney=").append(preMoney);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", type=").append(type);
        sb.append(", state=").append(state);
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
        Pref other = (Pref) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCateId() == null ? other.getCateId() == null : this.getCateId().equals(other.getCateId()))
            && (this.getBuyMoney() == null ? other.getBuyMoney() == null : this.getBuyMoney().equals(other.getBuyMoney()))
            && (this.getPreMoney() == null ? other.getPreMoney() == null : this.getPreMoney().equals(other.getPreMoney()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCateId() == null) ? 0 : getCateId().hashCode());
        result = prime * result + ((getBuyMoney() == null) ? 0 : getBuyMoney().hashCode());
        result = prime * result + ((getPreMoney() == null) ? 0 : getPreMoney().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}