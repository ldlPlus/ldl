package plus.ldl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import plus.ldl.jobs.JobDemo;

/**
 * @author ldl.plus
 * @date 2020年04月09日  22:03
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-jobs.xml");
        JobDemo jobDemo = (JobDemo) applicationContext.getBean("jobDemo");
        System.err.println("手动执行");
        System.err.println("手动执行");
        System.out.println(jobDemo);
        System.err.println("手动执行完毕");
        System.err.println("手动执行完毕");
    }
}
