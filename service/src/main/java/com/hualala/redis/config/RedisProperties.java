package com.hualala.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * Created by zhaoxiaodong on 2017/05/10.
 */
@lombok.Data
@Component
@ConfigurationProperties
public class RedisProperties {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.db}")
    private Integer db;

    @Value("${redis.pool.maxActive}")
    private Integer maxActive;

    @Value("${redis.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.pool.minIdle}")
    private Integer minIdle;

    @Value("${redis.pool.maxWait}")
    private Integer maxWait;
}
