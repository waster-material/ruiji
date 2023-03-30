package com.reggie_project.reggie.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie_project.reggie.Pojo.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DishMapper extends BaseMapper<Dish> {
}
