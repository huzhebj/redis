package com.hualala.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisListHandler {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：lpush
     * lpush redisList java
     */
    public Long leftPush(String key,String value){
        return redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 批量把一个数组插入到列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：lpush
     * lpush redisList1 java python c++ .net
     */
    public Long leftPushAll(String key,String[] values){
        return redisTemplate.opsForList().leftPushAll(key,values);
    }

    /**
     * 批量把一个集合插入到列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：lpush
     * lpush redisList1 java python c++ .net
     */
    public Long leftPushAll(String key,List<String> values){
        return redisTemplate.opsForList().leftPushAll(key,values);
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：lpushx
     * lpushx redisList3 4
     */
    public Long leftPushIfPresent(String key,String value){
        return redisTemplate.opsForList().leftPushIfPresent(key,value);
    }

    /**
     * 把value值放到key对应列表中pivot值的左面，如果pivot值存在的话
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：linsert
     * linsert redisList3 before 4 4.5
     */
    public Long leftPush(String key,String pivot,String value){
        return redisTemplate.opsForList().leftPush(key,pivot,value);
    }

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：rpush
     * rpush redisList2 0
     */
    public Long rightPush(String key,String value){
        return redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 批量把一个数组插入到列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：rpush
     * rpush redisList4 1 2 3 4
     */
    public Long rightPushAll(String key,String[] values){
        return redisTemplate.opsForList().rightPushAll(key,values);
    }

    /**
     * 批量把一个集合插入到列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：rpush
     * rpush redisList4 1 2 3 4
     */
    public Long rightPushAll(String key,List<String> values){
        return redisTemplate.opsForList().leftPushAll(key,values);
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：rpushx
     * rpushx redisList6 5
     */
    public Long rightPushIfPresent(String key,String value){
        return redisTemplate.opsForList().rightPushIfPresent(key,value);
    }

    /**
     * 把value值放到key对应列表中pivot值的右面，如果pivot值存在的话
     * 返回的结果为推送操作后的列表的长度
     * 对应redis命令：linsert
     * linsert redisList6 after 5 5.5
     */
    public Long rightPush(String key,String pivot,String value){
        return redisTemplate.opsForList().rightPush(key,pivot,value);
    }

    /**
     * 在列表中index的位置设置value值
     * 对应redis命令：lset
     * lset redisList1 1 c++++
     */
    public void set(String key,long index,String value){
        redisTemplate.opsForList().set(key,index,value);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     计数参数以下列方式影响操作：
     count> 0：从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
     count <0：从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     count = 0：移除表中所有与 value 相等的值。
     * 返回的结果为移除的元素的个数
     * 对应redis命令：lrem
     * lset redisList1 0 java
     */
    public long remove(String key,long count,String value){
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 根据下表获取列表中的值，下标是从0开始的
     * 对应redis命令：lindex
     * lindex redisList1 1
     */
    public String index(String key,long index){
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 弹出最左边的元素，弹出之后该值在列表中将不复存在
     * 返回弹出的元素
     * 对应redis命令：lpop
     * lpop redisList1
     */
    public String leftPop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 对应redis命令：blpop
     * blpop redisList1 2
     */
    public String leftPop(String key,long timeout, TimeUnit unit){
        return redisTemplate.opsForList().leftPop(key,timeout,unit);
    }

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     * 返回弹出的元素
     * 对应redis命令：rpop
     * rpop redisList2
     */
    public String rightPop(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 对应redis命令：brpop
     * brpop redisList2 2
     */
    public String rightPop(String key,long timeout, TimeUnit unit){
        return redisTemplate.opsForList().rightPop(key,timeout,unit);
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * 对应redis命令：rpoplpush
     * rpoplpush redisList3 redisList4
     */
    public String rightPopAndLeftPush(String key,String destinationKey){
        return redisTemplate.opsForList().rightPopAndLeftPush(key,destinationKey);
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * 对应redis命令：brpoplpush
     * brpoplpush redisList3 redisList4 3
     */
    public String rightPopAndLeftPush(String key,String destinationKey,long timeout, TimeUnit unit){
        return redisTemplate.opsForList().rightPopAndLeftPush(key,destinationKey,timeout,unit);
    }

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误
     * 对应redis命令：llen
     * llen redisList4
     */
    public Long size(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 修剪现有列表，使其只包含指定范围的元素，起始和停止都是基于0的索引
     * 对应redis命令：ltrim
     * ltrim redisList5 1 2
     */
    public void trim(String key,long start, long end){
        redisTemplate.opsForList().trim(key,start,end);
    }

    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素
     * 对应redis命令：lrange
     * lrange redisList4 1 -1
     */
    public List<String> range(String key,long start, long end){
        return redisTemplate.opsForList().range(key,start,end);
    }
}
