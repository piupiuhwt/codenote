package com.hwt.notes.thread.mutex;

import com.hwt.notes.utils.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class MutexExample {
    public static volatile long mutex = 0 ;

    public static List<Thread> threads = new ArrayList<>();

    public static volatile Long a = 12L;

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Field mutexField = MutexExample.class.getDeclaredField("mutex");
        long mutexOffset = unsafe.staticFieldOffset(mutexField);
        Thread t1 = new Thread(() -> {
            while (true){
                System.out.println(MutexExample.mutex+"--------------");
                while (MutexExample.mutex == 0){
                    unsafe.compareAndSwapLong(MutexExample.class, mutexOffset, 0, 1);
                    System.out.println(MutexExample.mutex);
                }
                if(MutexExample.mutex == 1){
                    System.out.println(" 1 ==================================");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MutexExample.mutex = 0;
                    for (Thread thread : threads) {
                        if (thread != Thread.currentThread()) {
                            unsafe.unpark(thread);
                        }
                    }
                }else {
                    unsafe.park(false,0);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true){
                System.out.println(MutexExample.mutex+"--------------");
                while (MutexExample.mutex == 0){
                    unsafe.compareAndSwapLong(MutexExample.class, mutexOffset, 0, 2);
                    System.out.println(MutexExample.mutex);
                }
                if(MutexExample.mutex == 2){
                    System.out.println("2 ==================================");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MutexExample.mutex = 0;
                    for (Thread thread : threads) {
                        if (thread != Thread.currentThread()) {
                            unsafe.unpark(thread);
                        }
                    }
                }else {
                    unsafe.park(false,0);
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true){
                System.out.println(MutexExample.mutex+"--------------");
                while (MutexExample.mutex == 0){
                    unsafe.compareAndSwapLong(MutexExample.class, mutexOffset, 0, 3);
                    System.out.println(MutexExample.mutex);
                }
                if(MutexExample.mutex == 3){
                    System.out.println("3 ==================================");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MutexExample.mutex = 0;
                    for (Thread thread : threads) {
                        if (thread != Thread.currentThread()) {
                            unsafe.unpark(thread);
                        }
                    }
                }else {
                    unsafe.park(false,0);
                }
            }
        });
        threads.add(t1);
        threads.add(t2);
        threads.add(t3);
        t1.start();
        t2.start();
        t3.start();
    }
}
