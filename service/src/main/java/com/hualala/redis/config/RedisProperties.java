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

    @Value("${smallflow.redis.host}")
    private String host;

    @Value("${smallflow.redis.port}")
    private Integer port;

    @Value("${smallflow.redis.db}")
    private Integer db;

    @Value("${smallflow.redis.pool.maxActive}")
    private Integer maxActive;

    @Value("${smallflow.redis.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${smallflow.redis.pool.minIdle}")
    private Integer minIdle;

    @Value("${smallflow.redis.pool.maxWait}")
    private Integer maxWait;
}
