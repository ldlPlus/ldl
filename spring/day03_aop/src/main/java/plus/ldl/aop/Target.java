package plus.ldl.aop;

/**
 * @author ldl.plus
 * @date 2020年03月11日  14:20
 */
public class Target implements TargetInterface {

    @Override
    public void save() {
        System.out.println("Target.save正在执行...");
    }
}
