package plus.ldl.entity;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author ldl.plus
 * @since 2020-03-12 10:25:00
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -33797438206455271L;

    private Integer id;

    private String name;

    private Integer balance;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}