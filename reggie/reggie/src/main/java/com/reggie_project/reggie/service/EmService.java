package com.reggie_project.reggie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_project.reggie.Dao.EmployeMapper;
import com.reggie_project.reggie.Pojo.Employee;
import com.reggie_project.reggie.service.Impl.ServiceEmployeeImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmService extends ServiceImpl<EmployeMapper,Employee> implements ServiceEmployeeImpl {
}
