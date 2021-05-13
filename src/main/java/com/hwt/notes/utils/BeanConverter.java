package com.hwt.notes.utils;

public interface BeanConverter {
    <T> T convertToBean(Class<T> beanClazz);
}
