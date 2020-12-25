package com.hwt.notes.algorithm.dp;

import java.util.Arrays;

public class DP {
    //dp表
    public int[] dpTable;

    /**
     * f(x)= 0   x=0
     *       -1  x<0
     *       (1+min(f(x-5),f(x-2),f(x-1))|| x ∈ {1，2，5})
     */
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 13;
        DP dp = new DP();
        System.out.println(dp.getNumOfCoins(coins, amount));
        System.out.println(Arrays.toString(dp.dpTable));
    }

    public int getNumOfCoins(int[] coins,int amount){
        //面值小于0反回-1
        if(amount<0){ return -1; }
        //面值等于0返回0
        if (amount == 0) { return 0; }
        //创建结果集
        dpTable = new int[amount + 1];
        //初始化第一个值
        dpTable[0] = 0;
        //初始化每一个值
        int max = Arrays.stream(coins).max().getAsInt();
        boolean[] flag = new boolean[max+1];
        for (int coin : coins) {
            flag[coin] = true;
        }
        for(int i = 1; i<= amount; i++){
            if(amount<=max&&flag[amount]){
                dpTable[amount] = 1;
                continue;
            }
            int temp = -1;
            for (int coin : coins) {
                int x = i-coin;
                if (x<0||dpTable[x]<0) {
                    continue;
                }
                int coinTemp = dpTable[x] + 1;
                temp = temp == -1 ? coinTemp : Math.min(temp, coinTemp);
            }
            dpTable[i] = temp;
        }
        return dpTable[amount];
    }
}
