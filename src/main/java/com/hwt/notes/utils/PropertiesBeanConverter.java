package com.hwt.notes.utils;

import java.util.Properties;

public class PropertiesBeanConverter implements BeanConverter{

    private Properties properties;
    private boolean couldConvert = false;

    public PropertiesBeanConverter(){
        properties = new Properties();
    }

    @Override
    public <T> T convertToBean(Class<T> beanClazz) {
        if(!couldConvert){
            return null;
        }

        return null;
    }
}
