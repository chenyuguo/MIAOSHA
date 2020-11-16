package com.miaosha.demo.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    public boolean exist (KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }
        finally {
            returnToPool(jedis);
        }
    }
    public <T> T get (KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T value = stringToBean(str,clazz);
            return value;
        }
        finally {
            returnToPool(jedis);
        }
    }

    public <T> String set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        String res = "";
        try{
            jedis = jedisPool.getResource();
            String strValue = beanToString(value);
            if(strValue == null || strValue.length() <= 0){
                return null;
            }
            String realKey = prefix.getPrefix() + key;
            int expiredSeconds = prefix.getExpiredSeconds();
            if(expiredSeconds <= 0){
                res = jedis.set(realKey,strValue);
            }
            else {
                res = jedis.setex(realKey,expiredSeconds,strValue);
            }
            return res;
        }
        finally {
            returnToPool(jedis);
        }
    }

    public Long incr (KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }
        finally {
            returnToPool(jedis);
        }
    }

    public Long decr (KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }
        finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return String.valueOf(value);
        }
        if(clazz == long.class || clazz == Long.class){
            return String.valueOf(value);
        }
        if(clazz == String.class){
            return (String)value;
        }
        else {
            return JSON.toJSONString(value);
        }


    }


    private <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }

        if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }
        if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }
        if(clazz == String.class){
            return (T)str;
        }
        else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    public void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
