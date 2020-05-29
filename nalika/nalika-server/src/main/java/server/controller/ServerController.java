package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.web.bind.annotation.*;
import plus.ldl.commons.pojo.ServerInfo;
import server.core.ClientManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月18日  23:33
 */
@RestController
@RequestMapping("/nalika/server")
public class ServerController implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private static final Logger log = LoggerFactory.getLogger(ServerController.class);
    @Value("${server.port:9524}")
    private int port;

    @Autowired
    private ClientManager clientManager;


    /**
     * 添加客户端节点
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public String register(HttpServletRequest request, @RequestBody ServerInfo clientInfo) {
        String clientIp = getIp(request);
        String clientUrl = "http://" + clientIp + ":" + clientInfo.getPort() + "/nalika/client";
        clientInfo.setIp(clientIp);
        clientInfo.setUrl(clientUrl);
        // 存入客户端URL与客户端name
        clientManager.setClients(clientInfo.getAppName(), clientInfo);
        log.info("clients:" + clientManager.getClients());
        return ServerInfo.OK;
    }

    /**
     * 返回所有客户端信息
     *
     * @return
     */
    @GetMapping("/getClients")
    public Map<String, List<ServerInfo>> getClients() {
        return clientManager.getClients();
    }

    /**
     * 心跳
     *
     * @return
     */
    @GetMapping("/pingpang")
    public String pingPang() {
        return ServerInfo.OK;
    }

    private String getIp(HttpServletRequest request) {
        // 操作者IP
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        return ip;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(port);
    }
}
