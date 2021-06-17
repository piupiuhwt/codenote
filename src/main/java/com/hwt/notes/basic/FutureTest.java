package com.hwt.notes.basic;

import com.sun.javafx.binding.StringFormatter;

import java.util.concurrent.*;

public class FutureTest {
    public static int showValue = 0;
    public static void main(String[] args) {

    }

    public static void lowBgeToUse(){
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            System.out.println("执行回调函数");
            showValue++;
            System.out.println("执行回调函数结束");
            return null;
        });
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            System.out.println("执行回调函数");
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            showValue++;
            System.out.println("执行回调函数结束");
            return ""+showValue;
        });
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        try {
            if(!stringFutureTask.isDone()) {
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            stringFutureTask.cancel(true);
            try{
                String s = stringFutureTask.get();
                System.out.println(s);
            }catch (CancellationException e){
                System.out.println("获取返回值取消");
            }
            System.out.println(showValue);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
