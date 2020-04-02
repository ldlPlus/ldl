package plus.ldl.aop.impl;

import org.springframework.stereotype.Component;
import plus.ldl.aop.Target;

/**
 * @author ldl.plus
 * @date 2020年03月11日  15:07
 */
@Component("target")
public class TargetImpl implements Target {
    @Override
    public void save() {
        System.out.println("TargetImpl.save正在保存中...");
    }

    @Override
    public void update() {
        System.out.println("TargetImpl.update");
    }

    @Override
    public void saheh() {
        System.out.println("TargetImpl.saheh");
    }
}
