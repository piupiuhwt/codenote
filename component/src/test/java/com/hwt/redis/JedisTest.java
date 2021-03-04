package com.hwt.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class JedisTest {
    public Jedis jedis;

    @Before
    public void init(){
        jedis = JedisPoolConnectRedis.getResource();
    }

    @Test
    public void testConnection(){
        System.out.println("jedis.ping() = " + jedis.ping());
        jedis.set("username", "ZhangSan");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
    }

}
