package plus.ldl.day01springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author ldl.plus
 * @date 2020年04月27日  15:57
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    /**
     *
     */
    private String age;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String[] address;

    @Override
    public String toString() {
        return "Person{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                '}';
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }
}
