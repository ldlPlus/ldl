package plus.ldl.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import plus.ldl.dao.UserDao;
import plus.ldl.dao.impl.UserDaoImpl;

/**
 * @author ldl.plus
 * @date 2020年03月08日  10:29
 */
public class UserDaoDemo {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserDao userDao1 = (UserDaoImpl) app.getBean("userDao");
        UserDao userDao2 = (UserDaoImpl) app.getBean("userDao");
        System.out.println(userDao1 == userDao2);

    }
}
