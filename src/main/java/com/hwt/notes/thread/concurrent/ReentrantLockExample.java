package com.hwt.notes.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static volatile String tName = null;

    public ReentrantLock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    public void criticalArea(){
        try {
            // 可中断式锁
            lock.lockInterruptibly();
            tName = Thread.currentThread().getName();
            try{
                System.out.println(tName);
                for(;;){
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            lock.unlock();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample rle = new ReentrantLockExample();
        Runnable r = () -> {
            rle.criticalArea();
            System.out.println("woshi"+Thread.currentThread().getName());
        };
        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        t1.start();
        t2.start();
        Thread.sleep(1000);

        while (tName == null){
            Thread.sleep(10);
        }
        Thread.sleep(10000);
        if(tName.equals("t1")){
            t2.interrupt();
            System.out.println(rle.lock.hasQueuedThread(t2));
        }

        if(tName.equals("t2")){
            t1.interrupt();
            System.out.println(rle.lock.hasQueuedThread(t1));
        }

    }
}
