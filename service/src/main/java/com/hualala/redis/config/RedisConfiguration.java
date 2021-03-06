package com.hualala.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xiangbin on 2016/10/19.
 */
@Configuration
public class RedisConfiguration {

    private Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Autowired
    private RedisProperties smallFlowRedisProperties;

    @Bean(name = "smallFlowRedisConnectionFactory")
    public JedisConnectionFactory smallFlowRedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(smallFlowRedisProperties.getMaxIdle());
        config.setMinIdle(smallFlowRedisProperties.getMinIdle());
        config.setMaxTotal(smallFlowRedisProperties.getMaxActive());
        factory.setPoolConfig(config);
        factory.setHostName(smallFlowRedisProperties.getHost());
        factory.setPort(smallFlowRedisProperties.getPort());
        factory.setDatabase(smallFlowRedisProperties.getDb());
        factory.setTimeout(smallFlowRedisProperties.getMaxWait()); //设置连接超时时间
        return factory;
    }

    @Bean(name = "smallFlowRedisTemplate")
    public RedisTemplate<String, String> smallFlowRedisTemplate(@Qualifier("smallFlowRedisConnectionFactory")RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template); //设置序列化工具
        return template;
    }

    private void setSerializer(StringRedisTemplate template) {
        StringRedisSerializer re = new StringRedisSerializer();
        template.setKeySerializer(re);
        template.setValueSerializer(re);
    }
}
