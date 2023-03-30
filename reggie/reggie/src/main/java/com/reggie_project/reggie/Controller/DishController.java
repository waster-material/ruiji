package com.reggie_project.reggie.Controller;

import  java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_project.reggie.Pojo.Category;
import com.reggie_project.reggie.Pojo.Dish;
import com.reggie_project.reggie.dtoDish.DtoDish;
import com.reggie_project.reggie.service.CategoryService;
import com.reggie_project.reggie.service.DishFlavorService;
import com.reggie_project.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
     @Autowired
     private DishService dishService;

     @Autowired
     private DishFlavorService dishFlavorService;

     @Autowired
     private CategoryService categoryService;

     @Autowired
     private CacheManager cacheManager;

     @PostMapping
     @CachePut(value = "DishName",key ="#dtodish")
     public R<String> save(@RequestBody DtoDish dtodish) {
               log.info("菜品添加数据.....",dtodish);
              dishService.saveWith(dtodish);
             return R.success("菜品添加成功");
     }

     @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
         Page<Dish> pages =new Page<>(page,pageSize);
         Page<DtoDish> dishPage =new Page<>();
         LambdaQueryWrapper<Dish> wrapper =new LambdaQueryWrapper<>();
         wrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
         wrapper.orderByDesc(Dish::getUpdateTime);
         dishService.page(pages,wrapper);

         BeanUtils.copyProperties(pages,dishPage,"records"); //拷贝pages

         List<Dish> records = pages.getRecords();
         List<DtoDish> list = records.stream().map(dish -> {
               DtoDish dtoDish =new DtoDish();

               BeanUtils.copyProperties(dish,dtoDish);  //属性值进行拷贝

               long categooryId = dish.getCategoryId(); //获取分类id
               Category category = categoryService.getById(categooryId);  //分类id查询分类数据
               if (category != null) {
                   String Categroyname = category.getName(); //获取菜品分类的名字
                   dtoDish.setCategoryName(Categroyname);   //菜品分类赋值给DTO的setCategoryName的值
               }
              return dtoDish;
           }).collect(Collectors.toList());
             dishPage.setRecords(list); //list集合封装的数据给DTO的POJO中 设置到dishPage中

         return R.success(dishPage);
     }

     @GetMapping("/{id}")
    public R<String> ById(@PathVariable long id){
         dishService.ById(id);
         return R.success("查询ID添成功");
     }

     @PutMapping
    public R<String> Update(@RequestBody Dish dish) {
         log.info(dish.toString());
         dishService.updateById(dish);
         return R.success("菜单修改成功");
     }


     @GetMapping("/list")
     @Cacheable(value = "DishName",key = "#dish.categoryId+'_'+#dish.status")
    public R<List<Dish>> list(Dish  dish) {   //  categoryId查询菜品套餐
         LambdaQueryWrapper<Dish> wrapper =new LambdaQueryWrapper<>();
         wrapper.eq(dish.getCategoryId() !=null, Dish::getCategoryId,dish.getCategoryId());
         wrapper.eq(Dish::getStatus,1);
         wrapper.orderByAsc(Dish::getSort).orderByAsc(Dish::getUpdateTime);
         List<Dish> list = dishService.list(wrapper);
         return R.success(list);
     }
}
