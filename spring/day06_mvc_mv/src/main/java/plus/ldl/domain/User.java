package plus.ldl.domain;

/**
 * @author ldl.plus
 * @date 2020年03月15日  10:45
 */
public class User {
    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String username;

    /**
     * age
     */
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
