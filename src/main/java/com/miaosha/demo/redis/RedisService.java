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

    public <T> T get (String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T value = stringToBean(str,clazz);
            return value;
        }
        finally {
            returnToPool(jedis);
        }
    }

    public <T> String set(String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String strValue = beanToString(value);
            if(strValue == null || strValue.length() <= 0){
                return null;
            }
            String res = jedis.set(key,strValue);
            return res;
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
