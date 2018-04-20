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

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration( exclude = RedisAutoConfiguration.class)
@SpringBootTest
public class Demo {

    @Autowired
    @Qualifier("smallFlowRedisTemplate")
    private RedisTemplate<String, String> smallFlowRedisTemplate;

    @Test
    public void demo() {
        String s1 = smallFlowRedisTemplate.opsForValue().get("SHOP_MIAOFU_BIND_OPENIDS_76058319");
        System.out.println("2018年，我在哗啦啦工作");
    }
}
