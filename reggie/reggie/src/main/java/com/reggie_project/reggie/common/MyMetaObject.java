package com.reggie_project.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
@Component
@Slf4j
public class MyMetaObject implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
         metaObject.setValue("createTime", LocalDateTime.now());
         metaObject.setValue("updateTime",LocalDateTime.now());
         metaObject.setValue("createUser",MyThreadLocal.getValue());
         metaObject.setValue("updateUser",MyThreadLocal.getValue());
//         metaObject.setValue("password", DigestUtils.md5Digest("123456".getBytes()));   //以免跟之前的save冲乎
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",MyThreadLocal.getValue());
    }
}
