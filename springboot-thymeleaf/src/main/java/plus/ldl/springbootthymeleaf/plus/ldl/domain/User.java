package plus.ldl.springbootthymeleaf.plus.ldl.domain;

/**
 * @author ldl.plus
 * @date 2020年06月09日  22:08
 */
public class User {
    private Integer id;
    private String name;
    private String address;

    public User() {
    }

    public User(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
