package server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ldl.plus
 * @date 2020年05月20日  10:55
 */
@Configuration
@ConfigurationProperties(prefix = ServerProperties.NALAKA_SERVER_PREFIX)
public class ServerProperties {
    public static final String NALAKA_SERVER_PREFIX = "nalika.server";

    private String serverHost;
    private int port;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
