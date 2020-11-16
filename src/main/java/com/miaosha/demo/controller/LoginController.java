package com.miaosha.demo.controller;

import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.domain.User;
import com.miaosha.demo.result.Result;
import com.miaosha.demo.service.MiaoshaUserService;
import com.miaosha.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public Result<Boolean> login(@RequestBody MiaoshaUser miaoshaUser){

        boolean res = miaoshaUserService.login(miaoshaUser).getData();
        return Result.success(res);
    }

    @RequestMapping("/register")
    public Result<Boolean> register(@RequestBody MiaoshaUser miaoshaUser){

        return miaoshaUserService.register(miaoshaUser);
    }








}
