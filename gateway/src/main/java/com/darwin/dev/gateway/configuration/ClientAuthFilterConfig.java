package com.darwin.dev.gateway.configuration;

import com.darwin.dev.distributed.crm.Client;
import com.darwin.dev.distributed.util.RequestCst;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ClientAuthFilterConfig implements GlobalFilter, Ordered {

    private static final String API_KEY_HEADER = "X-API-KEY";

    private final RedisTemplate<String, Client> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String apiKey = request.getHeaders().getFirst(API_KEY_HEADER);
        if (apiKey != null && !apiKey.isBlank()) {
            Client client = redisTemplate.opsForValue().get(apiKey);
            if (client != null) {
                URI uri = request.getURI();
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .queryParam(RequestCst.CLIENT_ID, client.id())
                        .build()
                        .toUri();
                ServerHttpRequest newRequest = request.mutate()
                        .uri(newUri)
                        .headers(httpHeaders -> httpHeaders.remove(API_KEY_HEADER))
                        .build();
                ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                return chain.filter(newExchange);
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
