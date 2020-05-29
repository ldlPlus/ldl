package plus.ldl.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * @author ldl.plus
 * @date 2020年05月29日  17:08
 * $VAR1
 */

/**
 * 讲师
 */
@ApiModel(value = "plus-ldl-domain-EduTeacher")
@TableName(value = "edu_teacher")
public class EduTeacher implements Serializable {
    /**
     * 讲师ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "讲师ID")
    private String id;

    /**
     * 讲师姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    /**
     * 讲师简介
     */
    @TableField(value = "intro")
    @ApiModelProperty(value = "讲师简介")
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    @TableField(value = "career")
    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @TableField(value = "level")
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    /**
     * 讲师头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     * TableLogic: 逻辑删除注解
     */
    @TableField(value = "is_deleted")
    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    /**
     * 获取讲师ID
     *
     * @return id - 讲师ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置讲师ID
     *
     * @param id 讲师ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取讲师姓名
     *
     * @return name - 讲师姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置讲师姓名
     *
     * @param name 讲师姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取讲师简介
     *
     * @return intro - 讲师简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置讲师简介
     *
     * @param intro 讲师简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取讲师资历,一句话说明讲师
     *
     * @return career - 讲师资历,一句话说明讲师
     */
    public String getCareer() {
        return career;
    }

    /**
     * 设置讲师资历,一句话说明讲师
     *
     * @param career 讲师资历,一句话说明讲师
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * 获取头衔 1高级讲师 2首席讲师
     *
     * @return level - 头衔 1高级讲师 2首席讲师
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置头衔 1高级讲师 2首席讲师
     *
     * @param level 头衔 1高级讲师 2首席讲师
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取讲师头像
     *
     * @return avatar - 讲师头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置讲师头像
     *
     * @param avatar 讲师头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取逻辑删除 1（true）已删除， 0（false）未删除
     *
     * @return is_deleted - 逻辑删除 1（true）已删除， 0（false）未删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置逻辑删除 1（true）已删除， 0（false）未删除
     *
     * @param isDeleted 逻辑删除 1（true）已删除， 0（false）未删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取更新时间
     *
     * @return gmt_modified - 更新时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置更新时间
     *
     * @param gmtModified 更新时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", intro=").append(intro);
        sb.append(", career=").append(career);
        sb.append(", level=").append(level);
        sb.append(", avatar=").append(avatar);
        sb.append(", sort=").append(sort);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
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
        EduTeacher other = (EduTeacher) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
                && (this.getCareer() == null ? other.getCareer() == null : this.getCareer().equals(other.getCareer()))
                && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
                && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
                && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getCareer() == null) ? 0 : getCareer().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }
}
