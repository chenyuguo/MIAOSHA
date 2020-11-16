package com.miaosha.demo.controller;

import com.miaosha.demo.domain.MiaoshaUser;
import com.miaosha.demo.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miaoshaUser")
public class MiaoshaUserController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("getUser/{id}")
    public MiaoshaUser getMiaoshaUserById(@PathVariable("id") long id){
        MiaoshaUser user = miaoshaUserService.getById(id);
        return user;
    }




}
