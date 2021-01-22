package com.hwt.notes.jvm.reflect;

import com.hwt.notes.annotation.WillChange;
import com.hwt.notes.entity.WillChangeBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class AnnotationChangeExample {
    public static void main(String[] args) {
        String value = "value";
        String newValue = "newValue";
        WillChange annotation = WillChangeBean.class.getAnnotation(WillChange.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        try {
            Field members = invocationHandler.getClass().getDeclaredField("memberValues");
            members.setAccessible(true);
            Map<String,Object> memMap = (Map<String, Object>) members.get(invocationHandler);
            Object valueObj = memMap.get(value);
            System.out.println(valueObj);
            memMap.put(value, newValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(annotation.value());
    }
}
