package com.hwt.notes.thread.threadpool;

import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(1000);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,5000, TimeUnit.SECONDS,workQueue,new MyThreadFactory());

    }

    static class MyThreadFactory implements ThreadFactory{
        private int counter = 0;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "my-factory-count" + counter++);
        }
    }
}
