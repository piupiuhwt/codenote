package com.hwt.notes.utils;

import sun.misc.Timer;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

public class UnsafeUtil {
    private static Unsafe unsafe;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(Unsafe.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Unsafe getUnsafe(){
        return unsafe;
    }

    public static void addressRelation(){
        // 获取unsafe
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        // 申请1024字节的空间
        long addr = unsafe.allocateMemory(1024);
        System.out.println("addr : " + addr);
        // 在申请空间的0位置填入long值12  long占 8 字节 每个字节由2个十六进制数表示
        unsafe.putAddress(addr,12);
        long num = unsafe.getAddress(addr);
        System.out.println("addr : " + num);
        // 一个long占8字节 所以 addr+8 不会和上面产生冲突
        unsafe.putAddress(addr+8,1);
        num = unsafe.getAddress(addr);
        System.out.printf("addr+8 : 0x%016x\n", num);
        System.out.println("addr+8 : " + num);
        // 从addr + 4 位置获取long值  所以值为 第一个long的高四字节和第二个long的低四字节组成
        num = unsafe.getAddress(addr+4);
        System.out.printf("addr+4 : 0x%016x\n", num);
        System.out.println("addr+4 : " + num);
        // 添加一个short试试
        unsafe.putShort(addr+16,(short)12);
        num = unsafe.getAddress(addr+16);
        System.out.println("addr+16 : "+num);
        //查看未赋值空间的默认值
        num = unsafe.getAddress(addr+512);
        System.out.println("addr+512 : " + num);
        //释放空间
        unsafe.freeMemory(addr);
        num = unsafe.getAddress(addr+8);
        System.out.println(num);
    }

    public static void threadRelation(){
        Thread t1 = new Thread(() -> {
            int i = 0;
            while(true){
                try {
                    Thread.sleep(500);
                    if(i > 30){
                        break;
                    }
                    System.out.println("t1 ------------ running");
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2 = new Thread(() -> {
            int i = 0;
            while(true){
                try {
                    Thread.sleep(500);
                    if(i == 10){
                        UnsafeUtil.getUnsafe().park(false,0);
                    }
                    if(i > 30){
                        break;
                    }
                    System.out.println("t2 ------------ running");
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(10000);
                UnsafeUtil.getUnsafe().unpark(t2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }

    public static void parkTest(){
        new Thread(() -> {
            int i = 0;
            while(true){
                if(i == 10){
                    UnsafeUtil.getUnsafe().park(true,1617864120000L);
                }
                if(i > 30){
                    break;
                }
                System.out.println("t2 ------------ running");
                i++;
            }
        }).start();
    }

    public static void memoryBarrier(){
        unsafe.loadFence();
    }

    public static void main(String[] args) {
//        addressRelation();
//        threadRelation();
        parkTest();
    }
}
