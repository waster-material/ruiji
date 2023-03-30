package com.reggie_project.reggie.Pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id ;
    private String name;
    private Long  categoryId;
    private BigDecimal price;
    private String code;
    private String image;
    private String description;
    private  Integer sort;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private long updateUser;
}
