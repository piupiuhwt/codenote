package com.hwt.notes.basic;

import cn.hutool.json.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTask2 {

    public static void main(String[] args) {
        test2();
        int a = -3;
        System.out.println(Integer.toBinaryString(a<< 28));
    }

    public static void test2(){
        JSONObject result = new JSONObject();
        FutureTask<JSONObject> futureTask = new FutureTask<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.set("result", "ok");
        }, result);
        new Thread(futureTask).start();
        long start = System.currentTimeMillis();
        while (result.get("result") == null){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(result.get("result"));
        System.out.println(end - start);
    }
}
