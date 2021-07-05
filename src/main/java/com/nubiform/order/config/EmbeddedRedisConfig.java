package com.nubiform.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    public EmbeddedRedisConfig(@Value("${spring.redis.port}") int redisPort) {
        log.info("EmbeddedRedisConfig: {}", redisPort);
        redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void startServer() {
        log.info("startServer");
        redisServer.start();
    }

    @PreDestroy
    public void stopServer() {
        log.info("stopServer");
        redisServer.stop();
    }
}
