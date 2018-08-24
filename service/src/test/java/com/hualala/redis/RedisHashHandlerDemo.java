package com.hualala.redis;

import com.hualala.redis.util.RedisHashHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisHashHandlerDemo {
    @Autowired
    private RedisHashHandler redisHashHandler;

    @Test
    public void put(){
        redisHashHandler.put("redisHash","name","jack");
        redisHashHandler.put("redisHash","age","10");
        redisHashHandler.put("redisHash","grade","100");
    }
}
