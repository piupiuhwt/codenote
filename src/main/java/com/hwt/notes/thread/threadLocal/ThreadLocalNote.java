package com.hwt.notes.thread.threadLocal;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * threadLocal 笔记
 * 首先先看下弱引用 {@link com.hwt.notes.jvm.reference.WeakReferenceNote}
 *
 * ThreadLocalMap 中的 Entry 特点  {@link com.hwt.notes.jvm.reference.WeakReferenceNote.ExtendedWeak}
 */
public class ThreadLocalNote {
    public ThreadLocal<InnerBean> myThreadBean = new ThreadLocal<>();

    public static void main(String[] args) {
        InnerBean innerBean = new InnerBean("liu",12, Lists.newArrayList("afdf",  "fadsfas"));
        ThreadLocalNote t = new ThreadLocalNote();

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(() -> {
                t.myThreadBean.set(innerBean);
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    InnerBean innerBean1 = t.myThreadBean.get();
                    innerBean1.setAge(finalI);
                    System.out.println(innerBean1);
                }
            }).start();
        }


    }
}
