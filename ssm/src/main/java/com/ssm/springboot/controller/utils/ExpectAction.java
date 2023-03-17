package com.ssm.springboot.controller.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常处理器
@RestControllerAdvice
public class ExpectAction {
     //拦截所有异常信息
    @ExceptionHandler(Exception.class)
    public R expectAction(Exception e) {
        e.printStackTrace();
       return new R("服务器出现异常,请重新等待");
    }
}
