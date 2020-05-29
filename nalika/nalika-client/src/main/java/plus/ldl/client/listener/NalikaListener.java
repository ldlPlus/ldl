package plus.ldl.client.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import plus.ldl.client.core.ServiceManager;

/**
 * @author ldl.plus
 * @date 2020年05月25日  16:25
 * 客户端监听器，实现启动项目时向服务器注册
 */
@Component
public class NalikaListener implements ApplicationRunner {

    @Autowired
    private ServiceManager serviceManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动监听 向服务端注册
        serviceManager.register();
    }
}
