package plus.ldl.q2cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ldl.plus
 * @date 2020年05月14日  13:02
 */

class User {
    private String username;
    private int age;

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

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User u1 = new User("z3", 22);
        User u2 = new User("li4", 33);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(u1);

        System.out.println(userAtomicReference.compareAndSet(u1, u2) + "\t" + userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(u1, u2) + "\t" + userAtomicReference.get());
    }
}
