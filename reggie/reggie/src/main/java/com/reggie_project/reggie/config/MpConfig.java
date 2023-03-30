package com.reggie_project.reggie.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//分页拦截器
@Configuration
public class MpConfig {
      @Bean
     public MybatisPlusInterceptor interceptors() {
          MybatisPlusInterceptor Interceptor =new MybatisPlusInterceptor();
          Interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
          return Interceptor;
      }
}
