package plus.ldl.excelpoijob.domain;

import java.io.Serializable;


/**
 * @author ldl.plus
 * @date 2020年05月13日  11:34
 * $VAR1
 */
public class Jianguocun implements Serializable, Comparable<Jianguocun> {
    private Integer id;

    private String relationship;

    private String name;

    private String idcard;

    private String phone;

    private String birthday;

    private String address;

    private String housenumberone;

    private String housenumbertwo;

    private String sex;

    private String hometown;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousenumberone() {
        return housenumberone;
    }

    public void setHousenumberone(String housenumberone) {
        this.housenumberone = housenumberone;
    }

    public String getHousenumbertwo() {
        return housenumbertwo;
    }

    public void setHousenumbertwo(String housenumbertwo) {
        this.housenumbertwo = housenumbertwo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "Jianguocun{" +
                "id=" + id +
                ", relationship='" + relationship + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", housenumberone='" + housenumberone + '\'' +
                ", housenumbertwo='" + housenumbertwo + '\'' +
                ", sex='" + sex + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }

    @Override
    public int compareTo(Jianguocun o) {
        int i = Integer.parseInt(this.getHousenumbertwo()) - Integer.parseInt(o.getHousenumbertwo());
        if (i == 0) {
            return this.id - o.id;
        }
        return i;
    }
}