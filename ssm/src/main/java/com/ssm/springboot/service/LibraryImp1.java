package com.ssm.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.springboot.dao.LibraryDao;
import com.ssm.springboot.pojo.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryImp1 extends ServiceImpl<LibraryDao, Library> implements ILibraryService{

    @Autowired
    private LibraryDao libraryDao;

    @Override
    public boolean saveLibrary(Library library) {
       return libraryDao.insert(library) > 0;
    }

    @Override
    public boolean removeLibrary(Integer ID) {
        return libraryDao.deleteById(ID) > 0;
    }

    @Override
    public boolean modify(Library library) {
         return libraryDao.updateById(library) > 0;
    }

    @Override
    public List<Library> check(Library library) {
         return libraryDao.selectList(null);
    }


}
