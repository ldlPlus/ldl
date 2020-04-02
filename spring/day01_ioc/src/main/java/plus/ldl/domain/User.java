package plus.ldl.domain;

/**
 * @author ldl.plus
 * @date 2020年03月08日  16:15
 */
public class User {
    private String username;
    private String addr;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
