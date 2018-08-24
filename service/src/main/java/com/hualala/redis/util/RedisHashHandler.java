package com.hualala.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHashHandler {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 对应redis命令：hset
     * hset redisHash name jack
     * hset redisHash age 10
     */
    public void put(String key,String hk,String hv){
        redisTemplate.opsForHash().put(key,hk,hv);
    }
}
