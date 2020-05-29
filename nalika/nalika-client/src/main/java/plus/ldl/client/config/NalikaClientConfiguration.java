package plus.ldl.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.ErrorHandler;
import org.springframework.web.client.RestTemplate;
import plus.ldl.client.core.ServiceManager;
import plus.ldl.client.interceptor.LoadBalancerInterceptor;
import plus.ldl.commons.pojo.ServerInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ldl.plus
 * @date 2020年05月25日  15:32
 */
@Configuration
public class NalikaClientConfiguration implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(NalikaClientConfiguration.class);
    /**
     * 服务名
     */
    @Value("${spring.application.name:UNKNOW}")
    private String appName;

    /**
     * 端口
     */
    @Value("${server.port:8080}")
    private int port;

    @Autowired
    private ClientProperties clientProperties;

    /**
     * 本地服务信息
     */
    @Bean
    @ConditionalOnMissingBean
    public ServerInfo serverInfo() {
        ServerInfo info = new ServerInfo();
        // 获取客户端服务名
        info.setAppName(appName);
        // 获取客户端端口
        info.setPort(port);
        // 获取服务端地址
        info.setUrl(clientProperties.getServiceUrl());
        // 获取客户端ip地址
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        assert localHost != null;
        String ip = localHost.getHostAddress();
        info.setIp(ip);

        log.info("nalika client registered info: " + info);
        return info;
    }

    /**
     * 客户端服务管理核心类
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceManager serviceManager(RestTemplate restTemplate, ServerInfo serverInfo) {
        return new ServiceManager(restTemplate, serverInfo);
    }

    /**
     * RestTemplate,配置负载均衡
     */
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoadBalancerInterceptor());
        return restTemplate;
    }

    /**
     * 配置定时任务线程池
     * <p>
     * 1、配置多线程
     * 2、增加错误预警
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler handler = new ThreadPoolTaskScheduler();
        handler.setPoolSize(20);
        handler.setThreadNamePrefix("nalika-thread-");
        handler.setAwaitTerminationSeconds(60);
        handler.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                log.error(this.getClass().getName(),t);
            }
        });
        handler.initialize();
        scheduledTaskRegistrar.setScheduler(handler);
    }
}
