package com.changgou.system.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ldl.plus
 * @date 2020年06月05日  21:39
 * 获取客户端的访问url
 */
@Component
public class UrlFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("经过了第二个过滤器...");
        String path = exchange.getRequest().getURI().getPath();
        log.info("path: " + path);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
