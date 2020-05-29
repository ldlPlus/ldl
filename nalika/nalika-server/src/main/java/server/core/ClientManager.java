package server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import plus.ldl.commons.pojo.ServerInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ldl.plus
 * @date 2020年05月28日  11:03
 */
@Component
public class ClientManager {
    private static final Logger log = LoggerFactory.getLogger(ClientManager.class);

    private Map<String, List<ServerInfo>> clients = new ConcurrentHashMap<>();
    private Map<String, List<ServerInfo>> activeClients = new ConcurrentHashMap<>();

    /**
     * 服务调用
     */
    @Autowired
    private RestTemplate restTemplate;

    public Map<String, List<ServerInfo>> getClients() {
        return clients;
    }

    public void setClients(String appName, ServerInfo clientInfo) {
        List<ServerInfo> clientsInfo = clients.get(appName);
        clientsInfo.add(clientInfo);
        this.clients.put(appName, clientsInfo);
    }

    public Map<String, List<ServerInfo>> getActiveClients() {
        return activeClients;
    }

    public void setActiveClients(String appName, ServerInfo activeClientsInfo) {
        List<ServerInfo> activeClientInfo = activeClients.get(appName);
        activeClientInfo.add(activeClientsInfo);
        this.activeClients.put(appName, activeClientInfo);
    }

    public void pingpang() {
        log.info("clients: " + clients);
        for (Map.Entry<String, List<ServerInfo>> entry : clients.entrySet()) {

            try {
                // TODO: 2020/5/28 重构有问题
                // 如果发送不超时说明没有问题
                restTemplate.postForEntity(entry.getKey(),
                        getClients(),
                        String.class);
                log.info("pingpang : success  -->" + entry.getKey());
                // 如果ping通了并且上一次没有ping通，取消移除队列
                if (getActiveClients().get(entry.getKey()) != null) {
                    getActiveClients().remove(entry.getKey());
                    log.info("pingpang : info client has been restored  -->" + entry.getKey());
                }
            } catch (RestClientException e) {
                // 如果map有此key说明上一次也没没ping通，可以移除
                if (getActiveClients().get(entry.getKey()) != null) {
                    getClients().remove(entry.getKey());
                    log.error("pingpang : error remove this client  -->" + entry.getKey());
                } else {
                    // 第一次没ping通，加入移除队列
                    log.warn("pingpang : warning the first failure  -->" + entry.getKey());
                    // setActiveClients(entry.getKey(), entry.getValue());
                }
            }
            // TODO: 2020/5/20 ping不通报错还是干嘛
        }
    }
}
