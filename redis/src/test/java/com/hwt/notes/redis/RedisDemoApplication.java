package com.hwt.notes.redis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
public class RedisDemoApplication {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void testInit(){
        String pong = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().ping();
        System.out.println("pong = " + pong);
    }

    @Test
    public void testString(){
        redisTemplate.opsForValue().set("username", "zhangsan");
        Object username = redisTemplate.opsForValue().get("username");
        System.out.println(username);
    }
}
