package com.hualala.redis.util;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisStringHandler {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    //set-->void set(K key, V value);
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    //set-->void set(K key, V value, long timeout, TimeUnit unit);
    public void set(String key, String value, long timeOut, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value,timeOut,unit);
    }

    //set-->void set(K key, V value, long offset);
    //该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
    /**
     * 举例：redisStringHandler.set("user_002", "002",60, TimeUnit.DAYS);  -->value:002
     * redisTemplate.opsForValue().set("user_002", "_李四",3);  -->value:002_李四
     */
    public void set(String key, String value, long offset){
        redisTemplate.opsForValue().set(key,value,offset);
    }

    //Boolean setIfAbsent(K key, V value);
    public Boolean setIfAbsent(String key,String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    //void multiSet(Map<? extends K, ? extends V> m); 为多个键分别设置它们的值
    public void multiSet(Map<String,String> map){
        redisTemplate.opsForValue().multiSet(map);
    }

    //Boolean multiSetIfAbsent(Map<? extends K, ? extends V> m);
    //为多个键分别设置它们的值，如果存在则返回false，不存在返回true
    public Boolean multiSetIfAbsent(Map<String,String> map){
        return redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    //V get(Object key);
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //V getAndSet(K key, V value); 设置键的字符串值并返回其旧值
    public String getAndSet(String key,String value){
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    //List<V> multiGet(Collection<K> keys); 为多个键分别取出它们的值
    public List<String> multiGet(List<String> keys){
        return redisTemplate.opsForValue().multiGet(keys);
    }

    //Long increment(K key, long delta); 支持整数
    //返回的是增加之后的数值
    public Long increment(String key,long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    //Double increment(K key, double delta); 也支持浮点数
    //返回的是增加之后的数值
    public Double increment(String key,double delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    //Integer append(K key, String value); 返回最终的value的值的长度
    //如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。
    //如果键不存在，则它被创建并设置为value的值，因此APPEND在这种特殊情况下将类似于SET。
    public Integer append(String key,String value){
        return redisTemplate.opsForValue().append(key,value);
    }

    //String get(K key, long start, long end);
    //截取key所对应的value字符串 0代表左侧第一个 1代表左侧第二个 -1代表右侧第一个 -2代表右侧第二个
    public String get(String key, long start, long end){
        return redisTemplate.opsForValue().get(key,start,end);
    }

    //Long size(K key); 返回key所对应的value值的长度
    public Long size(String key){
        return redisTemplate.opsForValue().size(key);
    }
}
