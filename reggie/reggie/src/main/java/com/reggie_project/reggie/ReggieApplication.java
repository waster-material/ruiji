package com.reggie_project.reggie;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Slf4j
@ServletComponentScan   //可以把过滤器放在spring容器
//@EnableTransactionManagement
//@Profile({"dev","pro","test"})
@EnableCaching   //开启缓存
public class ReggieApplication {
    public static void main(String[] args) {
        log.info("瑞吉外卖项目启动中....");
        SpringApplication.run(ReggieApplication.class, args);
    }
}
