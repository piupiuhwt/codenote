package com.hwt.notes.thread;

import com.hwt.notes.utils.UnsafeUtil;
import sun.misc.Unsafe;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ParkSleepWait {
    public static Unsafe unsafe = UnsafeUtil.getUnsafe();

    public static void main(String[] args) throws InterruptedException, IOException {
        waitTest();
    }

    /**
     * 用于测试 park 后的线程可不可以被interrupt中断
     * 结果为不可以
     */
    public static void parkSleep(){
        Thread t1 = new Thread(() -> {
            UnsafeUtil.getUnsafe().park(false, 0);
            System.out.println("===============");
        });

        try {
            Thread.sleep(1000);
            t1.interrupt();
            int read = System.in.read();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static volatile boolean flag = false;

    /**
     * 测试所谓的 “park”住的线程是否释放锁
     * park sleep 和锁就没关系 就不存在释不释放的问题 这两个都是线程的状态转换没有对某个对象的操作
     * synchronized 其实是获取到了该对象monitor 别的线程要是也想拿到它 就得排队等前面的线程用完
     * 调用wait方法时 会释放掉 monitor
     * 而sleep 和 park 单纯是线程操作 和这玩意毛的关系都没有
     */
    public static void synchronizedPark() throws InterruptedException {


        Thread t1 = new Thread(() -> {
            synchronized (ParkSleepWait.class) {
                unsafe.park(false, 0);
                System.out.println("已经 unpark ");
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (ParkSleepWait.class){
                if(!flag){
                    System.out.println("你没出synchronized我也进来了哈哈哈，park会放锁");
                }
            }
            System.out.println("终于到我运行了，555，你特喵的真损啊");
        }).start();
        TimeUnit.SECONDS.sleep(10);
        flag = true;
        unsafe.unpark(t1);
    }


    public static void waitTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (ParkSleepWait.class) {
                if (!flag) {
                    try {
                        unsafe.park(false, 0);
                        System.out.println("我要放monitor了");
                        ParkSleepWait.class.wait();
                        System.out.println("我继续。。。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            unsafe.unpark(thread);
            synchronized (ParkSleepWait.class){
                System.out.println("yes，我进来了，睡一会");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("醒了我叫下你");
                    ParkSleepWait.class.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
