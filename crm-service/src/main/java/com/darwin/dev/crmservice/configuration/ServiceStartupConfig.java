package com.darwin.dev.crmservice.configuration;

import com.darwin.dev.crmservice.core.service.IClientService;
import com.darwin.dev.distributed.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ServiceStartupConfig {
    private final RedisTemplate<String, Client> redisTemplate;
    private final IClientService clientService;

    @Bean
    public CommandLineRunner redisClientInfoInitializer() {
        List<Client> clients = clientService.getAllClients();
        return args -> {
            redisTemplate.opsForList().leftPushAll("clients", clients);
        };
    }
}
