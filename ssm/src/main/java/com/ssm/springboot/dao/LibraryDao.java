package com.ssm.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssm.springboot.pojo.Library;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LibraryDao extends BaseMapper<Library> {
}
