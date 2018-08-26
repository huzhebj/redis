package com.hualala.redis;

import com.hualala.redis.util.RedisListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisListHandlerDemo {
    @Autowired
    private RedisListHandler redisListHandler;

    @Test
    public void leftPush(){
        Long aLong = redisListHandler.leftPush("redisList", ".net");
        System.out.println(aLong);//4
    }

    @Test
    public void leftPushAll1(){
        String[] values = {"1","2","3"};
        Long aLong = redisListHandler.leftPushAll("redisList2", values);
        System.out.println(aLong);//3
    }

    @Test
    public void leftPushAll2(){
        List<String> values = new ArrayList<>();
        values.add("1");
        values.add("2");
        values.add("3");
        Long aLong = redisListHandler.leftPushAll("redisList3", values);
        System.out.println(aLong);//3
    }

    @Test
    public void leftPushIfPresent(){
        Long aLong = redisListHandler.leftPushIfPresent("redisList3", "5");
        System.out.println(aLong);//5
    }

    @Test
    public void leftPush2(){
        Long aLong = redisListHandler.leftPush("redisList3", "3","3.5");
        System.out.println(aLong);//7
    }

    @Test
    public void rightPush(){
        Long aLong = redisListHandler.rightPush("redisList2", "-1");
        System.out.println(aLong);//5
    }

    @Test
    public void rightPushAll(){
        String[] values = {"1","2","3","4"};
        Long aLong = redisListHandler.rightPushAll("redisList5", values);
        System.out.println(aLong);//4
    }

    @Test
    public void rightPushAll2(){
        List<String> values = new ArrayList<>();
        values.add("4");
        values.add("3");
        values.add("2");
        values.add("1");
        Long aLong = redisListHandler.rightPushAll("redisList6", values);
        System.out.println(aLong);//4
    }

    @Test
    public void rightPushIfPresent(){
        Long aLong = redisListHandler.rightPushIfPresent("redisList6", "6");
        System.out.println(aLong);//6
    }

    @Test
    public void rightPush2(){
        Long aLong = redisListHandler.rightPush("redisList6", "4","4.5");
        System.out.println(aLong);//8
    }

    @Test
    public void set(){
       redisListHandler.set("redisList1", 1,"c");
    }

    @Test
    public void remove(){
        long remove = redisListHandler.remove("redisList1", -2, "java");
        System.out.println(remove);//2
    }

    @Test
    public void index(){
        String s = redisListHandler.index("redisList1", 1);
        System.out.println(s);//.net
    }

    @Test
    public void leftPop(){
        String s = redisListHandler.leftPop("redisList1");
        System.out.println(s);//.net
    }

    @Test
    public void leftPop2(){
        String s = redisListHandler.leftPop("redisList1",5, TimeUnit.SECONDS);
        System.out.println(s);//null
    }

    @Test
    public void rightPop(){
        String s = redisListHandler.rightPop("redisList2");
        System.out.println(s);//-1
    }

    @Test
    public void rightPop2(){
        String s = redisListHandler.rightPop("redisList2",5, TimeUnit.SECONDS);
        System.out.println(s);//null
    }

    @Test
    public void rightPopAndLeftPush(){
        String s = redisListHandler.rightPopAndLeftPush("redisList3","redisList4");
        System.out.println(s);//2
    }

    @Test
    public void rightPopAndLeftPush2(){
        String s = redisListHandler.rightPopAndLeftPush("redisList3","redisList4",5,TimeUnit.SECONDS);
        System.out.println(s);//null
    }

    @Test
    public void size(){
        Long size = redisListHandler.size("redisList4");
        System.out.println(size);//11
    }

    @Test
    public void trim(){
        redisListHandler.trim("redisList4",2,-1);
    }

    @Test
    public void range(){
        List<String> list4 = redisListHandler.range("redisList4", 1, -1);
        System.out.println(list4);//[3.5, 3, 2, 1, 1, 2, 3, 4]
    }
}
