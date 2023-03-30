package com.reggie_project.reggie.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_project.reggie.Pojo.Category;
import com.reggie_project.reggie.Pojo.Setmeal;
import com.reggie_project.reggie.Pojo.SetmealDish;
import com.reggie_project.reggie.dtoDish.DToSetmeal;
import com.reggie_project.reggie.service.CategoryService;
import com.reggie_project.reggie.service.SetmealDishService;
import com.reggie_project.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;
     @PostMapping
     @CachePut(value = "SetmealName",key = "#dToSetmeal.id")
    public R<String> save (@RequestBody DToSetmeal dToSetmeal){
         log.info(dToSetmeal.toString());
       setmealService.saveWith(dToSetmeal);
        return  R.success("菜单套餐添加成功");
     }

    @GetMapping("/page")
   public R<Page> page(int page,int pageSize,String name) {
         Page<Setmeal> pages =new Page<>(page,pageSize);
         Page<DToSetmeal> setmealPage =new Page<>();
        LambdaQueryWrapper<Setmeal> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        wrapper.orderByAsc(Setmeal::getUpdateTime);
        setmealService.page(pages,wrapper);

        BeanUtils.copyProperties(pages,setmealPage,"records");
    List<Setmeal>  records  = pages.getRecords();
     List<DToSetmeal> list = records.stream().map( setmeal ->{
           DToSetmeal dToSetmeal =new DToSetmeal();
           BeanUtils.copyProperties(setmeal,dToSetmeal);

         Long categoryId = dToSetmeal.getCategoryId();
         Category setmealDishes = categoryService.getById(categoryId);
           if (setmealDishes !=null) {
               String CatmegoryName = setmealDishes.getName();
               dToSetmeal.setCategoryName(CatmegoryName);
           }
         return dToSetmeal;
       }).collect(Collectors.toList());
     setmealPage.setRecords(list);
      return R.success(setmealPage);
    }

    @DeleteMapping
    @CacheEvict(value = "SetmealName",key = "#root.args[0]",allEntries = true) // allEntries 删除当前所用缓存数据
    public R<String> delete(@RequestParam List<Long> ids){
       setmealService.deleteWth(ids);
       return R.success("删除成功");
    }
}
