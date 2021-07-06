package com.nubiform.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Profile({"local", "test"})
@Configuration
public class EmbeddedRedisConfig {

    private final RedisProperties redisProperties;

    private final RedisServer redisServer;

    public EmbeddedRedisConfig(RedisProperties redisProperties) {
        log.info("EmbeddedRedisConfig: {}", redisProperties);
        this.redisProperties = redisProperties;
        this.redisServer = new RedisServer(redisProperties.getPort());
    }

    @PostConstruct
    public void startServer() {
        log.info("startServer: {}", redisProperties);
        redisServer.start();
    }

    @PreDestroy
    public void stopServer() {
        log.info("stopServer");
        redisServer.stop();
    }
}
