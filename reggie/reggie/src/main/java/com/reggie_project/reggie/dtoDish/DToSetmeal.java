package com.reggie_project.reggie.dtoDish;

import com.reggie_project.reggie.Pojo.Setmeal;
import com.reggie_project.reggie.Pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class DToSetmeal extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
