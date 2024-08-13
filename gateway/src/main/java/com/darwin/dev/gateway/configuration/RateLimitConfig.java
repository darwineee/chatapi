package com.darwin.dev.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RateLimitConfig {

    @Primary
    @Bean("crm-rl")
    public RedisRateLimiter crmRateLimiter() {
        return new RedisRateLimiter(50, 60);
    }

    @Bean("chat-rl")
    public RedisRateLimiter chatRateLimiter() {
        return new RedisRateLimiter(500, 600);
    }
}
