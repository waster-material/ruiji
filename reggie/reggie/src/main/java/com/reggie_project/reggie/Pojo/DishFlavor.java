package com.reggie_project.reggie.Pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DishFlavor implements Serializable {
    private static final long serialVersionUID = 1L;
     private Long id;
     private Long dishId;
     private String name;
     private String  value;
    private Integer isDeleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private long updateUser;
}
