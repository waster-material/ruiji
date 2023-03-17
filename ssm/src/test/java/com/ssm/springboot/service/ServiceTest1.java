package com.ssm.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.springboot.pojo.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest1 {
    @Autowired
    private LibraryImp1 libraryImp1;
     @Test
    public void test() {
         System.out.println(libraryImp1.getById(2));
     }
    @Test
    public void saveTest() {
        Library library =new Library();
        library.setID(12);
        library.setType("计算机理论");
        library.setName("springMVC");
        library.setDescription("springMvc技术");
        libraryImp1.save(library);
    }
    @Test
    void deletetext() {
        libraryImp1.removeById(11);
    }
    @Test
    void updatetext() {
        Library library =new Library();
        library.setID(12);
        library.setType("计算机理论");
        library.setName("springboot");
        library.setDescription("springMvc开发");
         libraryImp1.updateById(library);
    }
    @Test
    void selecttext() {
        System.out.println(libraryImp1.list());
    }

    @Test
    void Pagetext() {
          IPage<Library> getpage =new Page<Library>(2,5);
        libraryImp1.page(getpage);
        System.out.println(getpage.getPages());
        System.out.println(getpage.getCurrent());
        System.out.println(getpage.getSize());
        System.out.println(getpage.getTotal());
        System.out.println(getpage.getRecords());
    }
}
