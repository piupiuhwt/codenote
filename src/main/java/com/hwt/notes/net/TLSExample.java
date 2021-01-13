package com.hwt.notes.net;

import sun.misc.Unsafe;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;

public class TLSExample {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchFieldException, IllegalAccessException {
        SSLContext instance = SSLContext.getInstance("TLSv1.2");
        Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
        unsafe.setAccessible(true);
        Unsafe unsafeInstance = (Unsafe) unsafe.get(Unsafe.class);
        byte aByte = unsafeInstance.getByte   (12L);
        System.out.println(aByte);
    }
}
