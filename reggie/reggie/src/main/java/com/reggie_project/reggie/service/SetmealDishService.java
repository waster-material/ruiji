package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.SetmealDishMapper;
import com.reggie_project.reggie.Pojo.SetmealDish;
import com.reggie_project.reggie.service.Impl.SetmealDishServiceImpI;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishService extends ServiceImpl<SetmealDishMapper,SetmealDish> implements SetmealDishServiceImpI {
}
