package plus.ldl.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import plus.ldl.config.SpringConfiguration;
import plus.ldl.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ldl.plus
 * @date 2020年03月08日  14:40
 */
public class UserController {
    public static void main(String[] args) throws SQLException {
        // ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = app.getBean("userServiceImpl", UserService.class);
        DataSource dataSource = app.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        userService.save();
    }
}
