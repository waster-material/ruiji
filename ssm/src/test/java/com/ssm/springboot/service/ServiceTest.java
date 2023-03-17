package com.ssm.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.springboot.pojo.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private LibraryService libraryService;
     @Test
    public void test() {
         System.out.println(libraryService.selectID(1));
     }
    @Test
    public void insertTest() {
        Library library =new Library();
        library.setID(12);
        library.setType("计算机理论");
        library.setName("springMVC");
        library.setDescription("springMvc技术");
        libraryService.inserts(library);
    }
    @Test
    void deletetext() {
       libraryService.deletes(12);
    }
    @Test
    void updatetext() {
        Library library =new Library();
        library.setID(11);
        library.setType("计算机理论");
        library.setName("springboot");
        library.setDescription("springMvc开发");
        libraryService.updates(library);
    }
    @Test
    void selecttext() {
        System.out.println(libraryService.select(null));
    }

    @Test
    void Pagetext() {
        IPage<Library> getpage = libraryService.getpage(2, 5);
        System.out.println(getpage.getPages());
        System.out.println(getpage.getCurrent());
        System.out.println(getpage.getSize());
        System.out.println(getpage.getTotal());
        System.out.println(getpage.getRecords());
    }
}
