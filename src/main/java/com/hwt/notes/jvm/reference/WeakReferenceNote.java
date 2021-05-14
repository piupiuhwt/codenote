package com.hwt.notes.jvm.reference;

import com.hwt.notes.entity.WillChangeBean;
import lombok.Data;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * 弱引用示例
 */
public class WeakReferenceNote<T> {

    public static void main(String[] args){
        try {
            weakReferenceFeature();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------- 子类 测试 -------------");
        try {
            weakExtendedFeature();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弱引用所指向对象在没有强引用情况下的gc回收测试
     * @throws InterruptedException 线程唤醒中断
     */
    public static void weakReferenceFeature() throws InterruptedException {
        // 创建强引用对象
        WillChangeBean willChangeBean = new WillChangeBean();
        // 创建弱引用对象
        WeakReference<WillChangeBean> weak = new WeakReference<>(willChangeBean);
        // 没强引用和弱引用都存在时gc 观察 弱引用中的对象是否被回收
        System.gc();
        Thread.sleep(1000);
        // 结果可见未被回收
        System.out.println(Objects.requireNonNull(weak.get()).getName());
        // 去除强引用只留下弱引用
        willChangeBean = null;
        // 此时没经历gc 弱引用还不会被回收
        Thread.sleep(1000);
        System.out.println(Objects.requireNonNull(weak.get()).getName());
        // gc 触发 弱引用被回收 抛出空指针异常
        System.gc();
        System.out.println(Objects.requireNonNull(weak.get()).getName());
    }

    /**
     * 测试 弱引用的继承类特性
     */
    public static class ExtendedWeak extends WeakReference<WillChangeBean> {
        String name;

        public ExtendedWeak(WillChangeBean key,String name){
            super(key);
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }

    public static void weakExtendedFeature() throws InterruptedException {
        // 创建强引用对象
        WillChangeBean willChangeBean = new WillChangeBean();
        // 创建弱引用对象
        ExtendedWeak extendedWeak = new ExtendedWeak(willChangeBean, "ppp");
        // 没强引用和弱引用都存在时gc 观察 弱引用中的对象是否被回收
        System.gc();
        Thread.sleep(1000);
        // 结果可见未被回收
        System.out.println(Objects.requireNonNull(extendedWeak.get()).getName());
        System.out.println(extendedWeak.getName());
        // 去除强引用只留下弱引用
        willChangeBean = null;
        // 此时没经历gc 弱引用还不会被回收
        Thread.sleep(1000);
        System.out.println(Objects.requireNonNull(extendedWeak.get()).getName());
        System.out.println(extendedWeak.getName());
        // gc 触发 弱引用修饰的对象被回收 抛出空指针异常
        // 但是 未被弱引用修饰的成员并不会被回收
        System.gc();
        System.out.println(extendedWeak.getName());
        System.out.println(Objects.requireNonNull(extendedWeak.get()).getName());
    }


}
