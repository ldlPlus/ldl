package plus.ldl.client.scheduledtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import plus.ldl.client.core.ServiceManager;

/**
 * @author ldl.plus
 * @date 2020年05月25日  16:46
 * 定时发送心跳，一定时间未检测到服务器则报错，每次心跳更新客户端信息
 */
@Component
public class PingPangJob {

    @Autowired
    private ServiceManager serviceManager;

    /**
     * 更新服务列表，每20秒更新一次
     */
    @Scheduled(cron = "0/20 * * * * ?")
    public void update() {
        serviceManager.updateAllData();
    }

    /**
     * 心跳检测，每10秒检测一次
     */
    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    public void tick() {
        serviceManager.pingpang();
    }
}
