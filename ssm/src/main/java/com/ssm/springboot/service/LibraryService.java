package com.ssm.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssm.springboot.pojo.Library;

import  java.util.List;


public interface LibraryService {
    boolean inserts(Library library);
    boolean deletes(Integer ID);
    boolean updates(Library library);
   List<Library> select(Library library);
   Library selectID(Integer ID);
   IPage<Library> getpage(int current,int size);

   IPage<Library> getpage01(int current,int size,Library library);
}
