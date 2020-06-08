package com.changgou.system.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author ldl.plus
 * @date 2020年06月05日  21:36
 * 获取客户端的访问ip
 */
@Component
public class IpFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(IpFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("经过了第一个过滤器...");
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        log.info("ip: " + remoteAddress.getHostName());
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
