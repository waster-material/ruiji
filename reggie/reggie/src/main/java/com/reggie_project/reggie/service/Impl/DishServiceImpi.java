package com.reggie_project.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie_project.reggie.Pojo.Dish;
import com.reggie_project.reggie.dtoDish.DtoDish;

public interface DishServiceImpi extends IService<Dish> {
     void saveWith(DtoDish dtoDish);
     DtoDish ById(long id);
     void updateWith(DtoDish dtoDish);
}
