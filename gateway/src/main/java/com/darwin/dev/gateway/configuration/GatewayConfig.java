package com.darwin.dev.gateway.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Configuration
public class GatewayConfig {

    private final RedisRateLimiter crmRateLimit;

    private final RedisRateLimiter chatRateLimit;

    public GatewayConfig(
            @Qualifier("crm-rl") RedisRateLimiter crmRl,
            @Qualifier("chat-rl") RedisRateLimiter chatRl
    ) {
        this.crmRateLimit = crmRl;
        this.chatRateLimit = chatRl;
    }

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> {
            String ip = Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                    .map(address -> address.getAddress().getHostAddress())
                    .orElse("unknown");
            return Mono.just(ip);
        };
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            KeyResolver keyResolver
    ) {
        return builder.routes()
                .route("crm-service-v1", r -> r
                        .path(
                                "/api/v1/users/**",
                                "/api/v1/channels/**",
                                "/api/v1/userchannel/**",
                                "/api/v1/info/**"
                        )
                        .filters(f -> f
                                .requestRateLimiter(rl -> rl
                                        .setRateLimiter(crmRateLimit)
                                        .setKeyResolver(keyResolver)
                                )
                        )
                        .uri("http://localhost:8081")
                )
                .route("chat-service-v1", r -> r
                        .path("/api/v1/messages/**")
                        .filters(f -> f
                                .requestRateLimiter(rl -> rl
                                        .setRateLimiter(chatRateLimit)
                                        .setKeyResolver(keyResolver)
                                )
                        )
                        .uri("http://localhost:8082"))
                .build();
    }
}
