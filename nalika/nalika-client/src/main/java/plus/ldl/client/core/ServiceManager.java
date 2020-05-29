package plus.ldl.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import plus.ldl.commons.pojo.ServerInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ldl.plus
 * @date 2020年05月18日  19:44
 */
public class ServiceManager {
    private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);

    /**
     * 服务调用
     */
    private final RestTemplate restTemplate;
    /**
     * 客户端信息
     */
    private final ServerInfo serverInfo;
    /**
     * 注册中心controller网址
     */
    private final String URL_SERVER;
    private final String URL_REGISTER;
    private final String URL_GET_CLIENTS;
    private final String URL_PINGPANG;
    /**
     * 已注册客户端
     */
    private Map<String, List<ServerInfo>> serviceMap;

    /**
     * 服务器状态
     */
    private int statusCode = 0;

    /**
     * 轮询调用次数,AtomicInteger..
     */
    private int calls = 0;

    public ServiceManager(RestTemplate restTemplate, ServerInfo serverInfo) {
        this.restTemplate = restTemplate;
        this.serverInfo = serverInfo;
        this.URL_SERVER = this.serverInfo.getUrl() + "/server";
        this.URL_REGISTER = this.URL_SERVER + "/register";
        this.URL_GET_CLIENTS = this.URL_SERVER + "/getClients";
        this.URL_PINGPANG = this.URL_SERVER + "/pingpang";
        this.serviceMap = new ConcurrentHashMap<>();
        log.info(serverInfo.getAppName() + " complete initialization ...");
    }

    /**
     * 向服务器注册
     */
    public void register() {
        try {
            String resp = restTemplate.postForObject(URL_REGISTER, serverInfo, String.class);
            if (ServerInfo.ERROR.equals(resp)) {
                throw new RuntimeException("Server error, please check the server ！！！ [" + URL_SERVER + "]");
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            System.exit(-1);
        }
    }

    /**
     * 从服务器拉取所有客户端信息，
     */
    public void updateAllData() {
        try {
            serviceMap = restTemplate.getForObject(URL_GET_CLIENTS, Map.class);
        } catch (RestClientException e) {
            log.error(this.getClass().getName() + " failed to pull resources", e);
        }
    }

    /**
     * 心跳服务
     */
    public void pingpang() {
        String resp = restTemplate.getForObject(URL_PINGPANG, String.class);
        if (ServerInfo.OK.equals(resp)) {
            log.info(serverInfo.getUrl() + " server connection is normal ...");
        } else {
            // 失败一次statusCode+1
            log.warn(serverInfo.getUrl() + " server failed to connect for the " + (++statusCode) + " time ...");
        }
        // 最多允许失败2次，第三次失败
        if (statusCode > 2) {
            log.error(serverInfo.getUrl() + " The server dies, please restart the server...");
        }
    }

    /**
     * 轮询负载均衡
     *
     * @param appName 服务名
     * @return url
     */
    public String getServerUrl(String appName) {
        List<ServerInfo> clients = serviceMap.get(appName);
        if (clients == null || clients.size() == 0) {
            // 如果没有此客户端，向服务器查询最新并更新
            updateAllData();
        }
        clients = serviceMap.get(appName);
        if (clients == null || clients.size() == 0) {
            // 还是没有此客户端，直接报错
            throw new RuntimeException("no such client ...");
        }
        ServerInfo info = clients.get(calls++ % clients.size());
        return "http://" + info.getIp() + ":" + info.getPort();
    }

}
