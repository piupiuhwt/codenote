package com.hwt.notes.basic;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//进制问题
//java中默认没有无符号数
//所以记得 int类型 二进制表示时 最高位为1 表示负数
//所以下方得最后一个输出为false
//改正方法时在进制数据后方加个L使他变为long类型
public class RadixExample {
    public static void main(String[] args) {
        System.out.println(0xf0000000);
        System.out.println(0b11110000000000000000000000000000L);
        //上面输出是f0000000
        System.out.println(4026531840L == 0b11110000000000000000000000000000);
        //这个输出啥

        System.out.println("".equals(new String(new char[]{'a','c'}, 0, 0)));

        Map<String,Boolean> c = new HashMap();
        Boolean taskStatus = true;
        System.out.println(taskStatus == null);


        System.out.println(ManagementFactory.getOperatingSystemMXBean().getName());

        String useString = "/test/task_0/rule_234542/adfasdf12313fdgdg.pcap";
        Pattern pattern = Pattern.compile("/task_\\d+/");
        Matcher matcher = pattern.matcher(useString);
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(useString.substring(start+1, end-1));
            System.out.println("start : " + start + " end : " + end);
        }

    }

}
