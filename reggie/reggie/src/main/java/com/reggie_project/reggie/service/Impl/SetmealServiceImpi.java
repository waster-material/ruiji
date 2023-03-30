package com.reggie_project.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie_project.reggie.Pojo.Setmeal;
import com.reggie_project.reggie.dtoDish.DToSetmeal;
import java.util.List;
public interface SetmealServiceImpi extends IService<Setmeal> {
    void saveWith(DToSetmeal dToSetmeal);
    void deleteWth(List<Long> ids);
}
