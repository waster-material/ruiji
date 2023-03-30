package com.reggie_project.reggie.Pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回结果")
public class User {
     @ApiModelProperty("用户ID")
     private  Long id;
     @ApiModelProperty("用户姓名")
     private  String name;
     private  String phone;
     private String sex;
     private String idNumber;
     private String avatar;
     private Integer status;
}
