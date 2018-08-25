package com.hualala.redis;

import com.hualala.redis.util.RedisHashHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisHashHandlerDemo {
    @Autowired
    private RedisHashHandler redisHashHandler;

    @Test
    public void put(){
        redisHashHandler.put("redisHash2","name","tom");
        redisHashHandler.put("redisHash2","age","12");
        redisHashHandler.put("redisHash2","grade","90.5");
    }

    @Test
    public void delete(){
        String[] arr = {"name","age","grade"};
        Long count = redisHashHandler.delete("redisHash", arr);
        System.out.println(count);//3
    }

    @Test
    public void increment1(){
        Long count = redisHashHandler.increment("redisHash", "grade", 1);
        System.out.println(count);//91
    }

    @Test
    public void increment2(){
        Double count = redisHashHandler.increment("redisHash2", "grade", 1.0);
        System.out.println(count);//92.5
    }

    @Test
    public void entries(){
        Map<Object, Object> map = redisHashHandler.entries("redisHash2");
        System.out.println(map);//{age=12, grade=92.5, name=tom}
    }

    @Test
    public void get(){
        String s = redisHashHandler.get("redisHash2", "name");
        System.out.println(s);//tom
    }

    @Test
    public void hasKey(){
        Boolean aBoolean = redisHashHandler.hasKey("redisHash2", "name");
        System.out.println(aBoolean);//true
    }

    @Test
    public void putIfAbsent(){
        Boolean aBoolean = redisHashHandler.putIfAbsent("redisHash2", "name2","jack2");
        System.out.println(aBoolean);//true
    }

    @Test
    public void values(){
        List<Object> values = redisHashHandler.values("redisHash2");
        System.out.println(values);//true
    }

    @Test
    public void size(){
        Long size = redisHashHandler.size("redisHash2");
        System.out.println(size);//5
    }

    @Test
    public void keys(){
        Set<Object> keys = redisHashHandler.keys("redisHash2");
        System.out.println(keys);//[name, age, grade, name1, name2]
    }

    @Test
    public void putAll(){
        Map<String,String> map = new HashMap<>();
        map.put("name","lili");
        map.put("age","12");
        map.put("grade","94");
        redisHashHandler.putAll("redisHash3",map);
    }

    @Test
    public void multiGet(){
        List<Object> hashKeys = new ArrayList<>();
        hashKeys.add("name");
        hashKeys.add("age");
        hashKeys.add("grade");
        List<Object> hash3 = redisHashHandler.multiGet("redisHash3", hashKeys);
        System.out.println(hash3);//[lili, 12, 94]
    }
}
