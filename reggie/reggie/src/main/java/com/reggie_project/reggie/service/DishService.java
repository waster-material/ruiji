package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.DishMapper;
import com.reggie_project.reggie.Pojo.Dish;
import com.reggie_project.reggie.Pojo.DishFlavor;
import com.reggie_project.reggie.dtoDish.DtoDish;
import com.reggie_project.reggie.service.Impl.DishServiceImpi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService extends ServiceImpl<DishMapper,Dish> implements DishServiceImpi {

    @Autowired
    private DishFlavorService dishFlavorService;


    //新增菜品 保存对应的菜品口味数据
    @Override
    @Transactional
    public void saveWith(DtoDish dtoDish) {
        this.save(dtoDish); //菜品保存到对应菜品表  如果菜品表保存成功 就会生成一个id
        long floverid = dtoDish.getId(); //菜品id
        List<DishFlavor> flaver=  dtoDish.getFlavors(); //菜品口味保存到对应菜品口味表的数据
        flaver =flaver.stream().map(dishFlavor ->{
              dishFlavor.setDishId(floverid); //关联菜品表的id
              return dishFlavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flaver);
    }

    @Override
    public DtoDish ById(long id) {
          //通过id修改数据   如果浏览器要我们回显的数据是菜品表 和 菜品口味表 但是他们有关联了  他们这两个表都来自 DTO
         DtoDish dtoDish =new DtoDish();
         Dish  DishById = this.getById(id);  //获取客户端的id
         BeanUtils.copyProperties(dtoDish,DishById); //客户端的id复制到DTO 实体类

        LambdaQueryWrapper<DishFlavor> wrapper =new LambdaQueryWrapper<>();  //菜品口味的id和菜品的id进行比较 ->  条件查询
         wrapper.eq(DishFlavor::getDishId,dtoDish.getId());

        List<DishFlavor> list = dishFlavorService.list(wrapper);
        dtoDish.setFlavors(list);  //list集合封装数据DTO 设置到dtoDish
        return dtoDish;
    }

    @Override
    public void updateWith(DtoDish dtoDish) {

    }
}
