package com.reggie_project.reggie.dtoDish;

import com.reggie_project.reggie.Pojo.Dish;
import com.reggie_project.reggie.Pojo.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

   @Data
    public class DtoDish extends Dish {   //DTO 数据传输对象  用于展示层和服务层之间
        private  List<DishFlavor> flavors =new ArrayList<>();  //用于接受页面的数据进行封装
        private  String categoryName;
        private  Integer copies;
}
