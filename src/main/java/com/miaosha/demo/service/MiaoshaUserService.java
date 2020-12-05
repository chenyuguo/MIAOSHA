package com.miaosha.demo.service;

import com.miaosha.demo.dao.MiaoshaUserDao;
import com.miaosha.demo.domain.LoginUser;
import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.exception.GlobalException;
import com.miaosha.demo.redis.RedisService;
import com.miaosha.demo.redis.UserKey;
import com.miaosha.demo.result.CodeMsg;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.util.MD5Util;
import com.miaosha.demo.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;


    public MiaoshaUser getById(long id){
        MiaoshaUser user = miaoshaUserDao.getById(id);
        return user;
    }
    public MiaoshaUser getByToken(String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        return redisService.get(UserKey.token,token,MiaoshaUser.class);
    }
    public MiaoshaUser getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return miaoshaUserDao.getByName(name);
    }

    public Result<MiaoshaUser> login(LoginUser loginUser, String token) {
        MiaoshaUser tokenUser = getByToken(token);
        if(tokenUser != null) {
            return Result.success(tokenUser);
        }
        MiaoshaUser dbUser = getByName(loginUser.getName());
        if(dbUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbSalt = dbUser.getSalt();
        if(MD5Util.inputPassToDBPass(loginUser.getPassword(),dbSalt).equals(dbUser.getPassword())){
            String newToken = UUIDUtil.uuid();
            redisService.set(UserKey.token, newToken, dbUser);
            return Result.success(dbUser);
        }
        return Result.error(CodeMsg.PASSWORD_ERROR);
    }

    public Result<Boolean> register(MiaoshaUser miaoshaUser){
        Long inputID = miaoshaUser.getId();
        //生成随机盐，双层md5加密
        String selfSalt = String.valueOf(new Random(1).nextInt(100));
        String dbPassword = MD5Util.inputPassToDBPass(miaoshaUser.getPassword(),selfSalt);
        miaoshaUser.setSalt(selfSalt);
        miaoshaUser.setPassword(dbPassword);
        Boolean res = miaoshaUserDao.addMiaoshaUser(miaoshaUser);

        if(res == true){
            return Result.success(true);
        }
        return Result.error(CodeMsg.REGISTER_ERROR);
    }
}
