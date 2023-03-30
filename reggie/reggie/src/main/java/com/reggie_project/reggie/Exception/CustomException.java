package com.reggie_project.reggie.Exception;


//自定义异常
public class CustomException extends RuntimeException{
    public CustomException (String msg) {
        super(msg);
    }
}
