package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.SetmealMapper;
import com.reggie_project.reggie.Exception.CustomException;
import com.reggie_project.reggie.Pojo.Setmeal;
import com.reggie_project.reggie.Pojo.SetmealDish;
import com.reggie_project.reggie.dtoDish.DToSetmeal;
import com.reggie_project.reggie.service.Impl.SetmealServiceImpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealService extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealServiceImpi {
    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    @Transactional
    public void saveWith(DToSetmeal dToSetmeal) {
        this.save(dToSetmeal);
        long categoryId = dToSetmeal.getId();
        List<SetmealDish> setmealDishes = dToSetmeal.getSetmealDishes();
       setmealDishes = setmealDishes.stream().map(setmealDish -> {
           setmealDish.setSetmealId(categoryId);
             return setmealDish;
         }).collect(Collectors.toList());
       setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void deleteWth(List<Long> ids) {
       //查询套餐表的状态 也要对应的套餐的id 是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        long count = this.count(queryWrapper);
        if (count > 0){
            throw  new CustomException("当前正在售卖中,不能删掉除");
        }
        //   如果以上都不报错或者不抛出就可以删出  删除时要删除对应的关联表
        this.removeById(count);

         //删除对应的菜品套餐表
        LambdaQueryWrapper<SetmealDish> wrapper =new LambdaQueryWrapper<>();
        wrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(wrapper);
    }
}
