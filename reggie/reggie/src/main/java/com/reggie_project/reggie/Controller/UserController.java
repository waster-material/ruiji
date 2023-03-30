package com.reggie_project.reggie.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.reggie_project.reggie.Pojo.User;
import com.reggie_project.reggie.Utils.VerificationCode;
import com.reggie_project.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
     @Autowired
      private UserService userService;

      @PostMapping("/sendMsg")
     public R<String> sendMsg(HttpSession session, @RequestBody User user){
          String phone = user.getPhone();
          if (StringUtils.isNotEmpty(phone)){
              //随机生成的4为验证码
             String integerCode = VerificationCode.getVerificaion(4).toString();
              System.out.println(integerCode);

              //调用阿里云提供的短信服务api完成发送短信  这里个人用户申请不了阿里云短信服务的签名，所以这里在后台输出了
              //SMSUtils.sendMessage("","","","");

              //把验证码存起来  这里使用session来存放验证码，当然也可以存到redis
              session.setAttribute(phone,integerCode);
              return R.success("手机验证码发送成功");
         }
          return R.error("发送短信错误");
      }

      @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){   //返回值User 因为登录后要返回用户信息
          log.info(map.toString());
           //获取手机号
          String phone = map.get("phone").toString();

          //获取验证码
          String code = (String)map.get("code").toString();

          //从Session中获取保存的验证码
          Object codeInSession = session.getAttribute(phone);

          //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
//          if(codeInSession != null && codeInSession.equals(code)){
              //如果能够比对成功，说明登录成功

              LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
              queryWrapper.eq(User::getPhone,phone);
              //根据用户的手机号去用户表获取用户
              User user = userService.getOne(queryWrapper);
              if(user == null){
                  //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                  user = new User();
                  user.setPhone(phone);
                  user.setStatus(1); //可设置也可不设置，因为数据库我们设置了默认值
                  //注册新用户
                  userService.save(user);
              }else {
              //这一行容易漏。。保存用户登录状态
              session.setAttribute("user",user.getId()); //在session中保存用户的登录状态,这样才过滤器的时候就不会被拦截了
              return R.success(user);

           }
          return R.error("登录失败");
      }


  }

