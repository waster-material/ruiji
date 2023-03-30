package com.reggie_project.reggie.Exception;

import com.reggie_project.reggie.Controller.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalException {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
      public R<String> doExcept(java.lang.Exception e) {
        String message = e.getMessage();
//        if (e.getMessage().contains("Duplicate entry")) {
//            String[] split = message.split(",");
//            String msg = split[2] + "已存在";
//             return R.error(msg);
//        }
//          return R.error("未知失败");
        return R.error(e.getMessage());
    }


    @ExceptionHandler(CustomException.class)
    public R<String> CustomException(java.lang.Exception e) {
        log.info("custom exception",e.getMessage());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public R<String> FileNotFoundException(java.lang.Exception e) {
        log.info("custom exception",e.getMessage());
        return R.error(e.getMessage());
    }
}
