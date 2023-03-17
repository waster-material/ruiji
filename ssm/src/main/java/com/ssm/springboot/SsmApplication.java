package com.ssm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SsmApplication {
    public static void main(String[] args) {
//        String[] arg =new String[1];
//        arg[0] ="--server.port=30";
        //用于关闭热部署
        //System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(SsmApplication.class, args);
    }

}
