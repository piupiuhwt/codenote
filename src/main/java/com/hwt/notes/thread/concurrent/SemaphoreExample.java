package com.hwt.notes.thread.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10, false);
    }

}
