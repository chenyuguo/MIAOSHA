package com.miaosha.demo.result;


import java.io.Serializable;

public class CodeMsg implements Serializable {

    private int code;
    private String message;


    //登陆模块 5002xx
    public static CodeMsg NONE_LOGIN = new CodeMsg(500209, "未登录,请登陆");
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(500216, "注册失败");

    //商品模块 5003xx
    public static CodeMsg NONE_GOOD = new CodeMsg(500300, "没有该商品信息");

    //秒杀模块 5004xx
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500400, "重复秒杀");


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
