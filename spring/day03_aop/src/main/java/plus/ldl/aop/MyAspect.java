package plus.ldl.aop;

/**
 * @author ldl.plus
 * @date 2020年03月11日  14:20
 * 切面
 */
public class MyAspect {

    public void before() {
        System.out.println("MyAspect.before前置增强..." + System.currentTimeMillis());
    }

    public void after() {
        System.out.println("MyAspect.after后置增强..." + System.currentTimeMillis());
    }
}
