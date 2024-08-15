package com.darwin.dev.gateway.configuration;

import com.darwin.dev.distributed.crm.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientAuthFilterConfig implements GlobalFilter, Ordered {

    private final RedisTemplate<String, Client> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String apiKey = request.getHeaders().getFirst("X-API-KEY");
        if (apiKey != null && !apiKey.isBlank()) {
            Client client = redisTemplate.opsForValue().get(apiKey);
            if (client != null) {
                String path = request.getURI().getPath();
                if (path.startsWith("/api/v1/crm")) {
                    String newPath = path.replace("crm", String.valueOf(client.id()));
                    ServerHttpRequest newRequest = request.mutate()
                            .path(newPath)
                            .build();
                    ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                    return chain.filter(newExchange);
                } else {
                    return chain.filter(exchange);
                }
            }
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
