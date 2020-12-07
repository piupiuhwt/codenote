package com.hwt.notes.pattern.single;

/**
 * 注意 此种方式在第一次使用时初始化实例所以需要多线程进行考量
 */
public class LazySingletonPattern {
    private static LazySingletonPattern instance = null;

    private LazySingletonPattern(){}

    /**
     * 与多线程使用时需要保证返回的是同一个对象
     * @return 唯一对象
     */
    public static LazySingletonPattern getInstance(){
        //外层为了防止多次调用消耗性能
        if(instance == null){
            synchronized (LazySingletonPattern.class){
                //里层为确保从始至终只有一个实例被创建
                if (instance == null) {
                    instance = new LazySingletonPattern();
                }
            }
        }
        return instance;
    }

    public static void printSomething(){
        System.out.println("something");
    }
}
