package com.reggie_project.reggie.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_project.reggie.Pojo.Category;
import com.reggie_project.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryServic;
   @PostMapping
    public R<String> save( @RequestBody Category category) {
       categoryServic.save(category);
         return R.success("新增菜单成功");
   }

   @GetMapping("/page")
    public R<Page> pages(int page,int pageSize) {
       Page<Category> page1 =new Page<>(page,pageSize);
       LambdaQueryWrapper<Category> wrapper =new LambdaQueryWrapper();
       wrapper.orderByDesc(Category::getSort);
       categoryServic.page(page1);
       return R.success(page1);
   }
   @DeleteMapping
    public R<String> deleteByid(long id)  {
          categoryServic.deleteById(id);
       return R.success("分类信息删除成功");
   }
   @PutMapping
    public R<String> update(@RequestBody Category category) {
       log.info("修改前",category);
      categoryServic.updateById(category);
       return R.success("修改成功");
   }
   @GetMapping("/list")
   @Cacheable(value = "Category",key = "#category.id+'_'+#category.name")
    public R list (Category  category) {  //通过条件查询获取分类菜品数据
       LambdaQueryWrapper<Category> wrapper =new LambdaQueryWrapper();
        wrapper.eq(category.getType()!=null,Category::getType,category.getType());
        wrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
       List<Category> list = categoryServic.list(wrapper);
       return R.success(list);
   }
}
