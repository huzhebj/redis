package com.hualala.redis;

import com.alibaba.fastjson.JSONObject;
import com.hualala.redis.bean.User;
import com.hualala.redis.util.RedisStringHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisStringHandlerDemo {
    @Autowired
    private RedisStringHandler redisStringHandler;

    @Test
    public void set1() {
        User user = new User();
        user.setId("001");
        user.setName("张三");
        user.setAge(10);
        user.setAddress("河南.驻马店.泌阳县");
        redisStringHandler.set("user_001", JSONObject.toJSONString(user));
    }

    @Test
    public void set2() {
        redisStringHandler.set("user_002", "002",60, TimeUnit.DAYS);
    }

    @Test
    public void set3() {
        redisStringHandler.set("user_002", "_李四",3);
    }

    @Test
    public void setIfAbsent() {
        Boolean user_002 = redisStringHandler.setIfAbsent("user_002", "002");
        Boolean user_003 = redisStringHandler.setIfAbsent("user_003", "003");
        System.out.println(user_002);//false  user_002之前已经存在
        System.out.println(user_003);//true   user_003之前不存在
    }

    @Test
    public void multiSet(){
        Map<String,String> map = new HashMap<>();
        map.put("user_004","004");
        map.put("user_005","005");
        map.put("user_006","006");
        redisStringHandler.multiSet(map);
    }

    @Test
    public void multiSetIfAbsent(){
        Map<String,String> map = new HashMap<>();
        map.put("user_006","006");
        map.put("user_007","007");
        map.put("user_008","008");
        Boolean aBoolean = redisStringHandler.multiSetIfAbsent(map);
        System.out.println(aBoolean);//false  只要至少有一个key是存在的，其他不存在的key也会设置不成功
    }

    @Test
    public void get(){
        String user_001 = redisStringHandler.get("user_001");
        System.out.println(user_001);// {"address":"河南.驻马店.泌阳县","age":10,"id":"001","name":"张三"}
    }

    @Test
    public void getAndSet(){
        String user_002 = redisStringHandler.getAndSet("user_002","002");
        System.out.println(user_002);// 002_李四
    }

    @Test
    public void multiGet(){
        List<String> keys = new ArrayList<>();
        keys.add("user_002");
        keys.add("user_003");
        keys.add("user_004");
        keys.add("user_005");
        keys.add("user_006");
        System.out.println(redisStringHandler.multiGet(keys));// [002, 003, 004, 005, 006]
    }

    @Test
    public void increment1(){
        System.out.println(redisStringHandler.increment("count",2));//1002
    }

    @Test
    public void increment2(){
        System.out.println(redisStringHandler.increment("count",3.55));//1005.55
    }

    @Test
    public void append(){
        System.out.println(redisStringHandler.append("appendTest","world!"));
    }

    @Test
    public void get2(){
        //"appendTest" "helloworld!"
        System.out.println(redisStringHandler.get("appendTest",0,-1));//helloworld!
        System.out.println(redisStringHandler.get("appendTest",1,4));//ello
        System.out.println(redisStringHandler.get("appendTest",-3,-1));//ld!
    }

    @Test
    public void size(){
        System.out.println(redisStringHandler.size("appendTest"));//11
    }
}
