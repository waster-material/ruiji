package com.ssm.springboot.service.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.springboot.dao.LibraryDao;
import com.ssm.springboot.pojo.Library;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsmApplicationTests {
    @Autowired
     private LibraryDao libraryDao;
    @Test
    void contextLoads() {
        System.out.println(libraryDao.selectById(1));
    }
    @Test
    void inserttext() {
        Library library =new Library();
        library.setID(100);
        library.setType("计算机理论1");
        library.setName("springboot");
        library.setDescription("springMvc开发");
         libraryDao.insert(library);
    }
    @Test
    void deletetext() {
        libraryDao.deleteById(5);
    }
    @Test
    void updatetext() {
        Library library =new Library();
        library.setID(4);
        library.setType("计算机理论");
        library.setName("springboot");
        library.setDescription("springMvc开发");
          libraryDao.updateById(library);
    }
    @Test
    void selecttext() {
        System.out.println(libraryDao.selectList(null));
    }

    @Test
    void Pagetext() {
        IPage<Library> page =new Page(2,5);   // Ipage分页对象
        libraryDao.selectPage(page,null);
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
    }
    @Test
    void condtionalText() {
         QueryWrapper<Library> query =new QueryWrapper<>();
         query.like(Strings.isEmpty("4"),"name","4"); //Strings.isEmpty 判断是否有值
        libraryDao.selectList(query);
    }

    @Test
    void condtionalText01() {
        LambdaQueryWrapper<Library> query =new LambdaQueryWrapper<Library>();
//         if ("4"!= null) query.like(Library::getName,"5");
        query.like("4"!=null,Library::getName,"4");
        libraryDao.selectList(query);
    }
}
