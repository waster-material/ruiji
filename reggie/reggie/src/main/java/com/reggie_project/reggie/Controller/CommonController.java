package com.reggie_project.reggie.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

        @Value("${reggie.path}")
        private String basePath;

       @PostMapping("/upload")
       public R<String> upload(MultipartFile file)  { //file是临时文件
         log.info(file.toString());
           String originalFilename = file.getOriginalFilename(); //获取原文件
           String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); //去后缀名

           String filename = UUID.randomUUID().toString() +suffix; //UUID重新生成文件名

           System.out.println(file.getContentType());

           File dir  =new File(basePath);
           if (!dir.exists()){
               dir.mkdir();
           }

           try {
               file.transferTo(new File(basePath+filename)); // 临时文件存储到指定文件
           } catch (IOException e) {
                e.printStackTrace();
           }
           return R.success(filename);
       }

       @GetMapping("/download")
       public void download(String name,HttpServletResponse response) {
           try {
                 //通过输入流来获取文件内容
             FileInputStream fileInputStream =new FileInputStream(basePath+name);
                //通过输出流回显浏览器中 展示图片
              ServletOutputStream servletOutputStream =response.getOutputStream();
                //设置图片的格式
              response.setContentType("image/jpeg");
              int len =0;
              byte[] bytes =new byte[1024];
              while((len =fileInputStream.read(bytes))!= -1) {
                   servletOutputStream.write(bytes,0,len);
                  servletOutputStream.flush();
              }
              fileInputStream.close();
              servletOutputStream.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
}
