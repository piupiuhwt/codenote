package com.hwt.notes.thread;


import com.hwt.notes.utils.Function;

import java.io.IOException;


public class SupersedePrint {

    public static final String monitor = "monitor";

    public static void main(String[] args) {
        new Thread( () -> {
            doThing(() -> System.out.println("A"));
        }).start();
        new Thread( () -> {
            doThing(() -> System.out.println("B"));
        }).start();

    }

    public static void doThing(Function function){
        int i = 100;
        while (true){
            synchronized (monitor){
                function.call();
                i--;
                try {
                    monitor.notify();
                    if (i<=0) {
                        break;
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
