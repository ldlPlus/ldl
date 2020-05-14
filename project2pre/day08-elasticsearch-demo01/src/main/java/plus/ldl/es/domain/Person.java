package plus.ldl.es.domain;

/**
 * @author ldl.plus
 * @date 2020年04月27日  15:57
 */
public class Person {

    /**
     *
     */
    private String id;


    /**
     *
     */
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String address;

}
