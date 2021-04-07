package com.hwt.notes.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringPattern {
    private final static String tempString = "asdfasdfasdffffddsfssdcfafdddddddddddddssssssssasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssasddddddddddddddddddddddddddddddddddddddddddddddddddgggggggggggggggggggggggggsadfgsadgdasdgsdsdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123";
    private final static String target = "aaaaaaaaaaaaaaaaaaaaaaa123";


    public static void main(String[] args) {
        stringBF();
        stringSunday();
    }


    /**
     * 暴力破解方式
     */
    public static void stringBF(){
        long start = System.nanoTime();
        int sLength = tempString.length();
        int tLength = target.length();
        int head = 0;
        int pLength = 0;
        while(sLength-head>=tLength){
            if(tempString.charAt(head+pLength) != target.charAt(pLength)){
                pLength = 0;
                head++;//暴力算法
                continue;
            }
            pLength++;
            if(pLength == tLength){
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println("end = " + end);
        System.out.println("start = " + start);
        System.out.println("head = " + head);
        System.out.println("end-start = " + (end - start));
    }


    /**
     * sunday算法方式 时间复杂度大概在 n+m 最慢N^2 最快 N
     */
    public static void stringSunday(){
        Map<Character, List<Integer>> subMap = new HashMap<>();
        int sLength = tempString.length();
        int tLength = target.length();
        //构建字串查询表
        for(int l = tLength-1; l>=0; l--){
            char c = target.charAt(l);
            List<Integer> list = subMap.computeIfAbsent(c, k -> new ArrayList<>());
            list.add(l);
        }
        int head = 0;
        int pLength = 0;
        long start = System.nanoTime();
        //直到匹配串长度超过剩余被匹配串长度结束
        st: while(sLength-head >= tLength){
            //如果有匹配不上的位置
            if(tempString.charAt(head+pLength) != target.charAt(pLength)){
                //当当前位置+1大于被匹配串长度时匹配失败返回结束
                if((head+tLength) > (sLength-1)){
                    head = -1;
                    break;
                }
                pLength = 0;
                char last = tempString.charAt(head + tLength);
                List<Integer> positions = subMap.get(last);
                if (positions==null || positions.size()==0) {
                    head = head + tLength;
                    continue;
                }
                int tempHead = head;
                for (Integer position : positions) {
                    int pLen = 0;
                    head = tempHead;
                    head += tLength - position;
                    for(int i = 0;i<tLength;i++){
                        if (tempString.charAt(tempHead + i) != target.charAt(i)) {
                            break;
                        }
                        pLen++;
                    }
                    if (pLen == tLength) {
                        break st;
                    }
                }
                continue;
            }
            pLength++;
            if(pLength == tLength){
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println("head = " + head);
        System.out.println("end-start = " + (end - start));
    }


}
