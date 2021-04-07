package com.hwt.notes.utils;

public class NumberUtil {
    //返回小于等于此数的2的最大次方数的数值（正数）
    public static int exponentOf2(int number){
        int result = -1;
        while(number > 0) {
            result++;
            number >>= 1;
        }
        return result > 0 ? 1 << result : result;
    }

    public static void main(String[] args) {
        System.out.println("exponentOf2(0) = " + exponentOf2(0));
        System.out.println("exponentOf2(1) = " + exponentOf2(1));
        System.out.println("exponentOf2(8) = " + exponentOf2(8));
        System.out.println("exponentOf2(12) = " + exponentOf2(12));
    }
}
