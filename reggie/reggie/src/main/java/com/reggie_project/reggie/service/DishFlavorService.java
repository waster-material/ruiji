package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.DishFlavorMapper;
import com.reggie_project.reggie.Pojo.DishFlavor;
import com.reggie_project.reggie.service.Impl.DishFlavorServiceImpi;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorService extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorServiceImpi {
}
