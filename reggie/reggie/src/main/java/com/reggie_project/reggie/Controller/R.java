package com.reggie_project.reggie.Controller;


//该类主要用于前台发送后台的json的数据要统一

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class R<E>  implements Serializable {
    private static  final long serialVersionUID  =1L;

    private Integer code;

    private String msg;

    private E data;

    private Map map = new HashMap(); //动态数据

    public static <E> R<E> success(E object) {
        R<E> r = new R<E>();
        r.data = object;
       r.code = 1;
        return r;
    }

    public static <E> R<E> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code =0;
        return r;
    }

    public R<E> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}


