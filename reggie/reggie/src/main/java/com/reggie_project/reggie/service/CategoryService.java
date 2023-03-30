package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.CategoryMapper;
import com.reggie_project.reggie.Exception.CustomException;
import com.reggie_project.reggie.Pojo.Category;
import com.reggie_project.reggie.Pojo.Dish;
import com.reggie_project.reggie.Pojo.Setmeal;
import com.reggie_project.reggie.service.Impl.CategoryServiceImpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ServiceImpl<CategoryMapper,Category> implements CategoryServiceImpi {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    public void deleteById(long id) {   //这个方法主要是如果关联了套餐和菜品就不能删除
        LambdaQueryWrapper<Dish> wrapper1 =new LambdaQueryWrapper<>();
        wrapper1.eq(Dish::getCategoryId,id);
        long count1 = dishService.count(wrapper1);
        if (count1 > 0) {
                //成立 则剖出异常
             throw new CustomException("当前菜品已经关联,不能删除");
         }
       LambdaQueryWrapper<Setmeal> wrapper2 =new LambdaQueryWrapper<>();
        wrapper2.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count(wrapper2);
        if (count2 > 0){
            throw new CustomException("当前套餐已经关联,不能删除");
        }
        super.removeById(id); //真正的删除
    }
}
