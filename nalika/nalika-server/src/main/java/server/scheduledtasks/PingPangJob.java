package server.scheduledtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import server.core.ClientManager;

/**
 * @author ldl.plus
 * @date 2020年05月20日  11:11
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class PingPangJob {

    @Autowired
    private ClientManager clientManager;

    @Scheduled(cron = "0/10 * * * * ?")
    public void pingPang() {
        clientManager.pingpang();
    }
}
