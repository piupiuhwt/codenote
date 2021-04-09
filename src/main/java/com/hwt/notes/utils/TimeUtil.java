package com.hwt.notes.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final String nanoFormatZ =  "yyyy-MM-dd HH:mm:ss.SSSSSSZ";
    private static final String nanoFormat =  "yyyy-MM-dd HH:mm:ss.SSSSSS";
    private static final long startNano;
    private static final long startMill;
    private static final long startNanoTime;

    static {
        startMill = System.currentTimeMillis();
        startNano = System.nanoTime();
        startNanoTime = startMill * 1000_000;
    }

    /**
     * 返回当前纳秒时间
     * @return 当前纳秒时间有误差
     */
    public static long getNanoTimeLong(){
        long nano = System.nanoTime();
        long execNano = nano - startNano;
        return startNanoTime + execNano;
    }
    /**
     * 返回当前纳秒时间对应jdk1.8 时间类
     * @return 当前纳秒时间有误差
     */
    public static ZonedDateTime getNanoTime(){
        long nanoTime = getNanoTimeLong();
        long epochSecond = nanoTime / 1000_000_000;
        long nanoAdjustment = nanoTime % 1000_000_000;
        Instant instant = Instant.ofEpochSecond(epochSecond, nanoAdjustment);
        return ZonedDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
    }
    /**
     * 返回当前纳秒时间及时区字符串
     * @return 当前纳秒时间有误差
     */
    public static String getNanoTimeZString(){
        ZonedDateTime zoneNanoTime = getNanoTime();
        return DateTimeFormatter.ofPattern(nanoFormatZ).format(zoneNanoTime);
    }
    /**
     * 返回当前纳秒时间字符串
     * @return 当前纳秒时间有误差
     */
    public static String getNanoTimeString(){
        ZonedDateTime zoneNanoTime = getNanoTime();
        return DateTimeFormatter.ofPattern(nanoFormat).format(zoneNanoTime);
    }

    public static void main(String[] args) {
        System.out.println(getNanoTimeLong());
        System.out.println(getNanoTimeString());
    }

}
