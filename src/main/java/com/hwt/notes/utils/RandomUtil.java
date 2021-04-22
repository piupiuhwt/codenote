package com.hwt.notes.utils;

public class RandomUtil {
    /**
     * 左闭右开
     * @param rangeStart
     * @param rangeEnd
     * @return
     */
    public static Integer getInt(int rangeStart,int rangeEnd){
        return (int)(Math.random()*(rangeEnd-rangeStart)+rangeStart);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getInt(0, 11));
        }
    }
}
