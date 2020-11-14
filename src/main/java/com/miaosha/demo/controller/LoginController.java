package com.miaosha.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        return "";
    }

}
