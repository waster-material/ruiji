package com.ssm.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssm.springboot.pojo.Library;
import java.util.List;

public interface ILibraryService extends IService<Library> {
    boolean saveLibrary(Library library);
    boolean removeLibrary(Integer ID);
    boolean modify(Library library);
     List<Library> check(Library library);
}
