package com.miaosha.demo.controller;

import com.miaosha.demo.domain.User;
import com.miaosha.demo.redis.RedisService;
import com.miaosha.demo.redis.UserKey;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/getUser/{id}")
    public Result<User> dbGet(@PathVariable("id") int id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @RequestMapping("/redis/set/{key}")
    public String redisSet(@PathVariable("key") String key, @RequestBody User user){
        String res = redisService.set(UserKey.id, key,user);
        return res;
    }

    @RequestMapping("/redis/get/{key}")
    public User redisGet(@PathVariable("key") String key){
        User user = redisService.get(UserKey.id, key, User.class);
        return user;
    }

}
