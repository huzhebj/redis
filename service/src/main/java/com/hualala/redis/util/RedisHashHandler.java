package com.hualala.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 对应redis命令：hdel
     * hdel redisHash name age
     */
    public Long delete(String key,String[] arr){
        return redisTemplate.opsForHash().delete(key,arr);
    }

    /**
     * 通过给定的delta增加散列hashKey的值（整型）
     * 对应redis命令：hincrby
     * hincrby redisHash grade 2
     */
    public Long increment(String key,String hk,long delta){
        return redisTemplate.opsForHash().increment(key,hk,delta);
    }

    /**
     * 通过给定的delta增加散列hashKey的值（浮点数）
     * 对应redis命令：hincrbyfloat
     * hincrbyfloat redisHash2 grade 1.0
     */
    public Double increment(String key,String hk,double delta){
        return redisTemplate.opsForHash().increment(key,hk,delta);
    }

    /**
     * 获取整个哈希存储根据密钥
     * 对应redis命令：hgetall
     * hgetall redisHash2
     */
    public Map<Object, Object> entries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     * 对应redis命令：hget
     * hget redisHash2 name
     */
    public String get(String key,String hk){
        return String.valueOf(redisTemplate.opsForHash().get(key,hk));
    }

    /**
     * 确定哈希hashKey是否存在
     * 对应redis命令：hexists
     * hexists redisHash2 name
     */
    public Boolean hasKey(String key,String hk){
        return redisTemplate.opsForHash().hasKey(key,hk);
    }

    /**
     * 仅当hashKey不存在时才设置散列hashKey的值
     * 对应redis命令：hsetnx
     * hsetnx redisHash2 name jack
     */
    public Boolean putIfAbsent(String key,String hk,String hv){
        return redisTemplate.opsForHash().putIfAbsent(key,hk,hv);
    }

    /**
     * 获取整个哈希存储的值根据密钥
     * 对应redis命令：hvals
     * hvals redisHash2
     */
    public List<Object> values(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取key所对应的散列表的大小个数
     * 对应redis命令：hlen
     * hlen redisHash2
     */
    public Long size(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取key所对应的散列表的key
     * 对应redis命令：hkeys
     * hkeys redisHash2
     */
    public Set<Object> keys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 使多个散列字段设置到key对应的散列表中
     * 对应redis命令：hmset
     * hmset redisHash3 name lili age 12 grade 94
     */
    public void putAll(String key,Map<String,String> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * 使多个散列字段设置到key对应的散列表中
     * 对应redis命令：hmget
     * hmget redisHash3 name age grade
     */
    public List<Object> multiGet(String key,List<Object> hashKeys){
        return redisTemplate.opsForHash().multiGet(key,hashKeys);
    }
}
