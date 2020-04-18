package plus.ldl.jobs;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ldl.plus
 * @date 2020年04月09日  21:01
 * 测试定时任务
 */
@Component
public class JobDemo {
    public void run() {
        System.out.println(LocalDateTime.now() + "----->JobDemo.run<---我3秒执行一次");
    }

    public void clear() {
        System.out.println(LocalDateTime.now() + "----->JobDemo.clear<---我10秒执行一次");
    }
}
