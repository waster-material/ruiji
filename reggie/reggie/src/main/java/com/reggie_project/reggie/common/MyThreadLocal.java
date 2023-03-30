package com.reggie_project.reggie.common;

public class MyThreadLocal {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().threadId();
        }
    };

    public static long getValue() {
        return threadLocal.get();
    }

    public static void setValue(long value) {
        threadLocal.set(value);
    }
}
