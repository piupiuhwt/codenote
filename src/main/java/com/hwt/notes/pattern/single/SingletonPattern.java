package com.hwt.notes.pattern.single;

/**
 * 单例很强 就怕反射。。。。
 * 此种方式的单例模式不会产生线程安全问题
 */

public class SingletonPattern {

    private static SingletonPattern instance = new SingletonPattern();

    private SingletonPattern(){}

    public static SingletonPattern getInstance() {
        return instance;
    }

    public static void printSomething(){
        System.out.println("something");
    }
}
