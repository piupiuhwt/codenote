package com.hwt.notes.basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeFuture {
    public static void main(String[] args) {
        //创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "tf_" + count.getAndIncrement());
            }
        };
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                5000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        // 创建根任务

            //拆分根任务
            //创建子任务
            //执行回调
        // 根任务添加
    }
}
