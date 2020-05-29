package plus.ldl.client.interceptor;

import cn.hutool.core.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;
import plus.ldl.client.core.ServiceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author ldl.plus
 * @date 2020年05月25日  18:55
 * 负载均衡实现，拦截restTemplate的请求，替换url
 */
public class LoadBalancerInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoadBalancerInterceptor.class);

    @Autowired
    private ServiceManager serviceManager;

    @Autowired
    private SimpleClientHttpRequestFactory simpleClientHttpRequestFactory;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        Assert.state(serviceName != null, "Request URI does not contain a valid hostname: " + originalUri);
        String serverUrl = serviceManager.getServerUrl(serviceName);
        try {
            ClientHttpRequest httpRequest = simpleClientHttpRequestFactory.createRequest(new URI(serverUrl), request.getMethod());
            return execution.execute(httpRequest, body);
        } catch (URISyntaxException e) {
            log.error(this.getClass().getName(),e);
        }
        return null;
    }

}
