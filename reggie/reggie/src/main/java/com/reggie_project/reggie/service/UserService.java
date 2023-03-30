package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.UserMapper;
import com.reggie_project.reggie.Pojo.User;
import com.reggie_project.reggie.service.Impl.UserImpI;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> implements UserImpI {
}
