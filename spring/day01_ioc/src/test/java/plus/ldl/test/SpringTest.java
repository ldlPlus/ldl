package plus.ldl.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import plus.ldl.service.UserService;

/**
 * @author ldl.plus
 * @date 2020年03月10日  10:00
 * 测试创建容器的多种方法与配置文件拆分
 */
public class SpringTest {
    /**
     *
     */
    @Test
    public void test1() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService1 = (UserService) app.getBean("userService1");
        System.out.println("userService1 = " + userService1);

        UserService userService2 = app.getBean("userService1", UserService.class);
        System.out.println("userService2 = " + userService2);

        UserService userService3 = app.getBean(UserService.class);
        System.out.println("userService3 = " + userService3);
    }
}
