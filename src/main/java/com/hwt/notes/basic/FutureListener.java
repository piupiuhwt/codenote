package com.hwt.notes.basic;

import io.netty.channel.ChannelFutureListener;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.*;

public interface FutureListener<F extends Future<?>> {

    public void operationComplete(F future);

    @Data
    static class MyFuture<V> extends FutureTask<V>{
        private FutureListener<MyFuture<V>> listener;

        public MyFuture(Callable<V> callable,FutureListener<MyFuture<V>> listener) {
            super(callable);
            this.listener = listener;
        }

        public MyFuture(Runnable runnable, V result) {
            super(runnable, result);
        }

        @Override
        public void run() {
            super.run();
            listener.operationComplete(this);
        }
    }

    static void test(){
        FutureListener<MyFuture<String>> futureListener = (future) -> {
                try {
                    String t = future.get();
                    System.out.println(t);
                } catch (InterruptedException e) {
                    System.out.println("此次程序被取消");
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        };
        MyFuture<String> future = new MyFuture<String>(() -> {
            System.out.println("=== call ===");
            return "zz";
        },futureListener);
        new Thread(future).start();
    }

    public static void main(String[] args) {
        test();
    }
}
