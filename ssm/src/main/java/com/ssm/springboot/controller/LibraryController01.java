package com.ssm.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssm.springboot.controller.utils.R;
import com.ssm.springboot.pojo.Library;
import com.ssm.springboot.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/librarys")
public class LibraryController01 {
       @Autowired
      private LibraryService libraryService;
        @GetMapping
       public R getAll(Library library) {
           System.out.println("查询成功!!!");
             return new R(true,libraryService.select(null));
       }

    @PostMapping
    public R save(@RequestBody Library library) throws IOException{
        System.out.println("添加成功!!!");
//        R r=new R();
//        boolean flag = libraryService.inserts(library);
//        r.setFlag(flag);
//         return r;
        System.out.println("----");
        System.out.println("+----");
        System.out.println("+----");
        System.out.println("+----");
        boolean flag = libraryService.inserts(library);
        if (library.getName().equals("lcy")) throw new IOException();
        return new R(flag,flag ? "添加数据成功^_^" : "添加数据失败~~~");
    }

    @PutMapping
    public R update(@RequestBody Library library) {
          return new R(libraryService.updates(library));
    }
    @DeleteMapping("/{ID}")
    public R delete(@PathVariable Integer ID) {
        System.out.println("删除指定数据!!!");
        return new R(libraryService.deletes(ID));
    }

    @GetMapping("/{ID}")
    public R getID(@PathVariable Integer ID) {
        System.out.println("查询指定数据成功!!!");
        return new R(true,libraryService.selectID(ID));  //用flag是里面还有数据
    }

//    @GetMapping("/{current}/{pagesize}")
//    public R page(@PathVariable int current,@PathVariable int pagesize) {
//          System.out.println("分页查询成功!!!");
//        IPage<Library> pages = libraryService.getpage(current, pagesize);
//         if (current > pages.getPages()) {  //当前页码值大于最大页码值 就重新执行  让当前页码值作为最大页码值
//             pages = libraryService.getpage((int)pages.getPages(),pagesize);
//         }
//        return new R(true,pages);
//    }

      @GetMapping("/{current}/{pagesize}")
    public R page(@PathVariable int current,@PathVariable int pagesize,Library library) {
          System.out.println("library ==>"+library);
        IPage<Library> pages = libraryService.getpage(current, pagesize);
        return new R(pages !=null,pages);
    }
}
