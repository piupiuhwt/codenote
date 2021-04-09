package com.hwt.notes.utils;

public class PrintFormatUtil {
    public static void hexPrint(Number num){
        if(num instanceof Long){
            System.out.printf("0x%016x\n",num);
        }
        if(num instanceof Integer){
            System.out.printf("0x%08x\n",num);
        }
        if(num instanceof Byte){
            System.out.printf("0x%02x\n",num);
        }
    }

    public static void main(String[] args) {
        PrintFormatUtil.hexPrint(12L);
        PrintFormatUtil.hexPrint(12);
        PrintFormatUtil.hexPrint((byte)12);
    }
}
