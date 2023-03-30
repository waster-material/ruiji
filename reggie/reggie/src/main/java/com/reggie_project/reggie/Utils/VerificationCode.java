package com.reggie_project.reggie.Utils;


import com.reggie_project.reggie.Exception.CustomException;

import java.util.Random;

public class VerificationCode {
        //用于随机生成验证码
     public static Integer Verification(int length){
       Integer code = null;
          if (length ==4) {
             code =new Random().nextInt(9999);
              if (code < 1000){
                  code+=1000;
              }
          } else if (length == 6) {
            code =new Random().nextInt(99999);
               if (code < 10000){
                   code+=10000;
               }
          }else {
               throw new CustomException("只能输入4位或6位");
          }
          return code;
     }
     public static String getVerificaion(int length){
         Random rd=new Random();
         String hash = Integer.toHexString(rd.nextInt());
         String cubsh = hash.substring(0, length);
         return cubsh;
     }
}
