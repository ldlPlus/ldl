package plus.ldl.entity;

import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author ldl.plus
 * @since 2020-03-16 18:32:53
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -75550007786677526L;

    private Long id;

    private String rolename;

    private String roledesc;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", roledesc='" + roledesc + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

}