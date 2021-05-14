package com.hwt.notes.thread;

import com.hwt.notes.utils.UnsafeUtil;

public class ArrayVolatile {
    public static int[] a = {1,2,3,5,6};
    public static volatile int b = 10;

    public static void main(String[] args) {
        Object o = UnsafeUtil.getUnsafe().staticFieldBase(ArrayVolatile.class);
        System.out.println(o);

        Thread t2 = new Thread(() -> {
            int[] c = a;

                for (int i = 0; i < 5000; i++) {
                    b++;
                }

        });


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                b++;
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b);

    }

}
