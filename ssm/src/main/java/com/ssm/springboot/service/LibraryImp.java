package com.ssm.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.springboot.dao.LibraryDao;
import com.ssm.springboot.pojo.Library;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryImp implements LibraryService{

     @Autowired
     private  LibraryDao libraryDao;
    @Override
    public boolean inserts(Library library) {
         return libraryDao.insert(library) > 0;
    }

    @Override
    public boolean deletes(Integer ID) {
        return libraryDao.deleteById(ID) > 0;
    }

    @Override
    public boolean updates(Library library) {
        return libraryDao.updateById(library) > 0;
    }

    @Override
    public List<Library> select(Library library) {
          return libraryDao.selectList(null);
    }

    @Override
    public Library selectID(Integer ID) {
        return libraryDao.selectById(ID);
    }

    @Override
    public IPage<Library> getpage(int current, int size) {
        IPage<Library> page =new Page(current,size);
       libraryDao.selectPage(page,null);
       return page;
    }

    @Override
    public IPage<Library> getpage01(int current, int size, Library library) {
        LambdaQueryWrapper<Library>  QueryWrapper =new LambdaQueryWrapper<>();
       QueryWrapper.like(Strings.isEmpty(library.getName()),Library::getName,library.getName());
        QueryWrapper.like(Strings.isEmpty(library.getType()),Library::getType,library.getType());
        QueryWrapper.like(Strings.isEmpty(library.getDescription()),Library::getDescription,library.getDescription());
       IPage<Library> pages =new Page<>();
        libraryDao.selectPage(pages,QueryWrapper);
        return  pages;
    }
}
