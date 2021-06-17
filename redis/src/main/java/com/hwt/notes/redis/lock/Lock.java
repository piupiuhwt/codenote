package com.hwt.notes.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class Lock {
    private static final String LOCK_SUCCESS = "OK";
    private static final long UNLOCK_SUCCESS = 1L;

    public static boolean tryLock(Jedis jedis,String lockKey,String value,int timeout){
        while(true){
            String result = jedis.set(lockKey, value, SetParams.setParams().ex(timeout).nx());
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
        }
    }

    public static boolean unLock(Jedis jedis,String lockKey,String resultId){
        if (!jedis.get(lockKey).equals(resultId)) {
            return false;
        }
        Long result = jedis.del(lockKey);
        return result == UNLOCK_SUCCESS;
    }
}
