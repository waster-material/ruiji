package com.ssm.springboot.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mybatis_plusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor Interceptor =new MybatisPlusInterceptor();
         Interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return Interceptor;
    }
}
