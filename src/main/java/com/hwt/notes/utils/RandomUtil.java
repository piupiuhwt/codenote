package com.hwt.notes.utils;

import javax.persistence.criteria.CriteriaBuilder;

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

    public static boolean getLine2(int line2,int length){
        String line2BinaryString = Integer.toBinaryString(line2);
        int line2StringLen = line2BinaryString.length();
        if (line2StringLen < length) {
            return false;
        }
        String binaryString = line2BinaryString.substring(line2StringLen - length, line2StringLen);
        return binaryStringToInt(binaryString) == 47;
    }

    public static int binaryStringToInt(String binaryString){
        int stringLen = binaryString.length();
        int result = 0;
        for (int i = 0; i < stringLen; i++) {
            result |= ((binaryString.charAt(i) - '0') << (stringLen-i-1));
        }
        return result;
    }

    public static void main(String[] args) {
        int a = 17415215;

        System.out.println(getLine2(a, 9));
    }
}
