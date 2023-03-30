package com.reggie_project.reggie.Pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressBook {
    private Long id;
    private String userId;
    private String consignee;
    private Integer sex;
    private String phone;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private  String districtCode;
    private String districtName;
    private String detail;
    private String label;
    private Integer isDefault;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private long updateUser;
}
