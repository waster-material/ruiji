package com.reggie_project.reggie.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_project.reggie.Pojo.Employee;
import com.reggie_project.reggie.common.MyThreadLocal;
import com.reggie_project.reggie.service.EmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/employee")
@Slf4j
@Api(tags = "用户有关接口")
public class EmployeeController {
    @Autowired
   private EmService serviceEmployee;

    @Autowired
   private CacheManager cacheManager;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录的接口")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) throws IOException {
        String password = employee.getPassword(); // 获取客户端的密码 用md5对密码进行加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());  // 对密码进行加密

        LambdaQueryWrapper<Employee> Wrapper =new LambdaQueryWrapper();
        Wrapper.like(Employee::getUsername,employee.getUsername());
        Employee one = serviceEmployee.getOne(Wrapper);

        if (one == null) {  //如果用户为null的话 说明就已经登录过了
         return R.error("用户已经登录过");
        }

        if (!password.equals(one.getPassword())) {
            return R.error("密码错误");
        }

        if (one.getStatus() ==0) {
             return R.error("该账号已经被禁用");
        }

        request.getSession().setAttribute("employee",one.getId());  //到这里证明用户和密码都没问题
        return R.success(one);
    }


     @PostMapping("/logout")     //退出操作
    public R logout(HttpServletRequest request)  {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


     @PostMapping
     @CachePut(value = "CacheName",key = "#employee.id")  //添加缓存数据
    public R<String> save(HttpServletRequest request,  @RequestBody Employee employee){
         log.info("添加成功前",employee);
     employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes())); //客户端对这些没有添加字段进行初始化，如果不初始化就会null 因为pojo要和前台转入的数据要一致 不然就封装不到
//         employee.setCreateTime(LocalDateTime.now());
//         employee.setUpdateTime(LocalDateTime.now());
//          long employeeID = (long)request.getSession().getAttribute("employee");
//         System.out.println(employeeID);
//         employee.setCreateUser(employeeID);
//         employee.setUpdateUser(employeeID);
         serviceEmployee.save(employee);
         return R.success("添加成功");
     }

     @GetMapping("/page")
     @ApiImplicitParams({
          @ApiImplicitParam(name = "page",value = "页码",required = true),
          @ApiImplicitParam(name = "pageSize",value = "每页的页码条数",required = true),
          @ApiImplicitParam(name = "name",value = "姓名",required = false)
     })
    public R<Page> pages (int page,int pageSize,String name) {
         Page<Employee> page1 =new Page<>(page,pageSize);
         LambdaQueryWrapper<Employee> query =new LambdaQueryWrapper();
           query.like(StringUtils.isNotEmpty(name),Employee::getName,name);
           query.orderByDesc(Employee::getUpdateTime);
          serviceEmployee.page(page1,query);
          return R.success(page1);
     }

     @PutMapping
//     @CacheEvict(value = "cacheName",key = "#employee.id")
     @CacheEvict(value = "cacheName",key = "#root.args[0].id")
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("用户的状态 ",employee.toString());
       long employeeID =(long) request.getSession().getAttribute("employee");
        MyThreadLocal.setValue(employeeID);

//          employee.setUpdateTime(LocalDateTime.now());
//         employee.setUpdateUser(employeeID);
         serviceEmployee.updateById(employee);
          return R.success("用户修改成功 ");
     }

     @GetMapping("/{id}")
     @Cacheable(value = "cacheName",key = "#result.id",condition = "#result != null")  //用缓存数据 就返回缓存数据  没缓存数据 就执行下面这条语句  unless满足条件测不缓存
    public R<Employee> updateByid(@PathVariable long id) {
         Employee employee = serviceEmployee.getById(id);
         if (employee != null) {
             return R.success(employee);
         }
         return R.error("修改失败!");
     }

//     @CacheEvict(value = "cacheName",key = "#root.args[0]")   //清理缓存
//     @CacheEvict(value = "cacheName",key = "#p0")
     @CacheEvict(value = "cacheName",key = "#id")
     @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable long id) {
         serviceEmployee.removeById(id);
         return R.success("删除成功");
    }
}


