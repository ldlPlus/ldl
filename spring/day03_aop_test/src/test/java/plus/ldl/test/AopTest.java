package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.ldl.aop.Target;

/**
 * @author ldl.plus
 * @date 2020年03月11日  15:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AopTest {

    @Autowired
    private Target target;

    /**
     *
     */
    @Test
    public void test20() throws Exception {
        target.save();
        target.saheh();
    }

    /**
     *
     */
    @Test
    public void test33() throws Exception {
        target.update();
    }
}
