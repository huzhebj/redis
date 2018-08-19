package com.hualala.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration( exclude = RedisAutoConfiguration.class)
@SpringBootTest
public class Demo {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void demo() {
        redisTemplate.opsForValue().set("redis1","123456",10, TimeUnit.MINUTES);
        String redis1 = redisTemplate.opsForValue().get("redis1");
        System.out.println(redis1);
    }
}
