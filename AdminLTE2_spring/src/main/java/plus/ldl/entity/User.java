package plus.ldl.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (SysUser)实体类
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:29
 */
public class User implements Serializable {
    private static final long serialVersionUID = 476593860211638037L;

    private Long id;

    private String username;

    private String email;

    private String password;

    private String phonenum;

    private List<String> roleNames;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", roleNames=" + roleNames +
                '}';
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

}