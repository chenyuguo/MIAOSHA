package com.miaosha.demo.service;

import com.miaosha.demo.dao.MiaoshaUserDao;
import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.result.CodeMsg;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        MiaoshaUser user = miaoshaUserDao.getById(id);
        return user;
    }

    public Result<Boolean> login(MiaoshaUser miaoshaUser) {
        MiaoshaUser dbUser = miaoshaUserDao.getById(miaoshaUser.getId());
        if(dbUser.getId() == null){
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbSalt = dbUser.getSalt();
        if(MD5Util.inputPassToDBPass(miaoshaUser.getPassword(),dbSalt).equals(dbUser.getPassword())){
            return Result.success(true);
        }
        return Result.error(CodeMsg.PASSWORD_ERROR);
    }

    public Result<Boolean> register(MiaoshaUser miaoshaUser){
        Long inputID = miaoshaUser.getId();
        Boolean res = miaoshaUserDao.addMiaoshaUser(miaoshaUser);
        if(res == true){
            return Result.success(true);
        }
        return Result.error(CodeMsg.REGISTER_ERROR);
    }
}
