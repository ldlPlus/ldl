package server.config;

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

/**
 * @author ldl.plus
 * @date 2020年05月25日  15:32
 */
@Configuration
public class NalikaServerConfiguration implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(NalikaServerConfiguration.class);
    /**
     * 服务名
     */
    @Value("${spring.application.name:UNKNOW}")
    private String appName;

    /**
     * 端口
     */
    @Value("${server.port:9524}")
    private int port;

    @Autowired
    private ServerProperties serverProperties;


    /**
     * RestTemplate,配置负载均衡
     */
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
                log.error(this.getClass().getName(), t);
            }
        });
        handler.initialize();
        scheduledTaskRegistrar.setScheduler(handler);
    }
}

