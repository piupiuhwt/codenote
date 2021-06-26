package com.hwt.notes.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();
    private ReentrantReadWriteLock.ReadLock rl = rwl.readLock();

    private volatile int count = 0;

    public void addCount(){
        wl.lock();
        try {
            String tName = Thread.currentThread().getName();
                count++;
                System.out.println(tName + " : "+ count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wl.unlock();
    }

    public void getCount(){
        rl.lock();
        try{
            String tName = Thread.currentThread().getName();
            System.out.println(tName + " 读取到的值为: " + count);
        }catch (Exception e){
            e.printStackTrace();
        }
        rl.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample rwle = new ReadWriteLockExample();
        Runnable r = () -> {
            while (true){
                rwle.addCount();
                rwle.getCount();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        Thread t3 = new Thread(r, "t3");
        Thread t4 = new Thread(() -> {
            while (true){
                rwle.getCount();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
