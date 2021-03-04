package com.hwt.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolConnectRedis {
    public static final String REDIS_HOST = "192.168.149.129";
    public static final int REDIS_PORT = 6379;
    public static final String REDIS_PASSWORD = "111111";
    public static JedisPool jedisPool;

    static {
        //创建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数 默认8
        jedisPoolConfig.setMaxTotal(5);
        //设置最大空闲数 默认8
        jedisPoolConfig.setMaxIdle(5);
        //设置最小空闲数 默认0
        jedisPoolConfig.setMinIdle(0);
        //设置等待时间
        jedisPoolConfig.setMaxWaitMillis(100);
        //初始化JedisPool对象
        jedisPool = new JedisPool(jedisPoolConfig, REDIS_HOST, REDIS_PORT, 10000, REDIS_PASSWORD);

    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }
}
