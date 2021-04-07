package com.hwt.notes.thread;

public class TestLockThis {
    public static synchronized void print2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("2 = " + 2);
    }

    public void doNothing() throws InterruptedException {
        synchronized (TestLockThis.class){
            Thread.sleep(1000000);
        }
    }

    public synchronized void print() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("1 = " + 1);
    }

    public static void main(String[] args) {
        TestLockThis testLockThis = new TestLockThis();
        new Thread(() -> {
            try {
                testLockThis.doNothing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            while (true){
                try {
                    TestLockThis.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
