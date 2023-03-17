package com.ssm.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.springboot.pojo.Library;
import com.ssm.springboot.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RestController
@RequestMapping("/librarys")
public class LibraryController {
       @Autowired
      private LibraryService libraryService;
        @GetMapping
       public List<Library> getAll(Library library) {
           System.out.println("查询成功!!!");
             return libraryService.select(null);
       }

    @PostMapping
    public boolean save(@RequestBody Library library) {
        System.out.println("添加成功!!!");
        return libraryService.inserts(library);
    }

    @PutMapping
    public boolean update(@RequestBody Library library) {
        return libraryService.inserts(library);
    }
    @DeleteMapping("/{ID}")
    public boolean delete(@PathVariable Integer ID) {
        System.out.println("删除指定数据!!!");
        return libraryService.deletes(ID);
    }

    @GetMapping("/{ID}")
    public Library getID(@PathVariable Integer ID) {
        System.out.println("查询指定数据成功!!!");
        return libraryService.selectID(ID);
    }

    @GetMapping("/{current}/{pagesize}")
    public IPage<Library> page(@PathVariable int current,@PathVariable int pagesize) {
              System.out.println("分页查询成功!!!");
             return libraryService.getpage(current,pagesize);
    }
}
