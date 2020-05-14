package plus.ldl.registry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ldl.plus
 * @date 2020年05月06日  21:51
 */
@ConfigurationProperties(prefix = "ldl9524.client")
public class TemplateConfig {

    private String prefix = "ldl9524.client";
    private String defaultUrl = "http://localhost:9524/ldl9524/";
    private String hostname;

    @Value("server.port")
    private String port;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
