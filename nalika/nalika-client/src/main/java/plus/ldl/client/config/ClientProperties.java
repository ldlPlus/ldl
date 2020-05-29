package plus.ldl.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年05月25日  15:38
 * 客户端配置信息
 */
@Configuration
@ConfigurationProperties(prefix = ClientProperties.NALAKA_CLIENT_PREFIX)
public class ClientProperties {
    public static final String NALAKA_CLIENT_PREFIX = "nalika.client";

    /**
     * 注册中心服务器地址，默认本机端口9524
     */
    private String serviceUrl="http://localhost:9524/nalika";

    /**
     * 服务刷新频率，默认10，单位秒
     *//*
    private int serviceUpdateFrequency=10;

    *//**
     * 心跳检测频率，默认30，单位秒
     *//*
    private int pingpangDetectionFrequency=30;
*/

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}

