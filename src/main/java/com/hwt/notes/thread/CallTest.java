package com.hwt.notes.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.LongAdder;

public class CallTest {
    public static void main(String[] args) {
        longAddrTest();
    }

    public static void longAddrTest(){
        LongAdder longAdder = new LongAdder();
        longAdder.add(100L);
        System.out.println(longAdder.longValue());
        longAdder.add(-10L);
        System.out.println(longAdder.longValue());
    }

    public static void FutureTest(){
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            for(int i = 0; i < 2000; i++){
                Thread.sleep(10);
            }
            return "wo完事了hahahahaha";
        });
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
