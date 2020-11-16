package com.miaosha.demo.exception;

import com.miaosha.demo.result.CodeMsg;
import com.miaosha.demo.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    public Result<String> exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        return Result.error(CodeMsg.SESSION_ERROR);
    }
}
