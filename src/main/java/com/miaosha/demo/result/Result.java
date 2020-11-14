package com.miaosha.demo.result;

public class Result <T> {
    private int code;
    private String msg;
    private T data;


    public <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public <T> Result<T> error (CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    public Result(CodeMsg codeMsg){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMessage();
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
