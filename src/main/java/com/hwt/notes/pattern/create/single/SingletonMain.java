package com.hwt.notes.pattern.create.single;

import java.lang.reflect.Field;

/**
 * 在主类里进行一些玩耍
 * lazy方式和普通方式的区别在于实例初始化的时机
 * 普通方式在调用静态方法时就会初始化他的实例 如
 * @see java.lang.Runtime
 * 而Lazy方式则是在调用getInstance function 时初始化实例
 *
 * 而单例要解决的问题是实例对象的唯一性
 * 所以引出对两种方式的思考（可能想多了）：谁更好一点或者说谁更适合什么样的场景
 *
 * 单例对象若为整个系统各个时期 一定会调用的对象 普通模式更为合适
 * 若此单例对象并不一定会被使用到则lazy方式更加合适
 *
 * 单纯个人理解 如有问题 还请指正
 */
public class SingletonMain {

    public static void main(String[] args) {
        //用于查看初始化时机
        LazySingletonPattern.printSomething();
        isInitializeInstance(LazySingletonPattern.class);
        SingletonPattern.printSomething();
        isInitializeInstance(SingletonPattern.class);

    }

    /**
     * 判断实例是否初始化
     * @param clazz 单例类class
     */
    public static void isInitializeInstance(Class<?> clazz){
        try {
            Field instance = clazz.getDeclaredField("instance");
            instance.setAccessible(true);
            Object o = instance.get(clazz);
            if (o==null) {
                System.out.println("此类实例并未初始化");
                return;
            }
            System.out.println("此类对象实例已被初始化："+instance.toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
