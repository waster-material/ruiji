package com.ssm.springboot.controller.utils;

import lombok.Data;


// 这个类主要是前后端数据统一
@Data
public class R {
    private  Boolean flag;
    private  Object data;
    private String message;
     public R() {

     }
     public R(Boolean flag){
         this.flag =flag;
     }
     public R(Boolean flag,Object date) {
         this.flag =flag;
         this.data =data;
     }
     public R(String message) {
         this.flag =false;
         this.message =message;
     }
}
