package com.hwt.notes.algorithm;

/**
 * 取特殊值操作
 */
public class SpecialValue {
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,3,2,1,3,3,2,4,2};
        System.out.println(special2(arr));
        System.out.println(special1(arr, 3));
    }

    /**
     * 获取一组数中某数只出现一次其他数出现n次，找出出现一次得数
     * @return 出现过一次得特殊值
     */
    public static int special1(int[] arr,int appearTimes){
        int[] perBitTimes = new int[32];
        for (int i : arr) {
            for (int j = 0; j < 32; j++) {
                perBitTimes[j] = perBitTimes[j] + ((i >> j) & 1);
            }
        }
        int result = 0;
        for (int j = 0; j < 32; j++) {
            result |= (perBitTimes[j]%appearTimes)<<j;
        }
        return result;
    }

    /**
     * 逢三化零
     * @param arr
     * @param appearTimes
     * @return 特殊值
     */
    public static int special2(int[] arr){
        int twice = 0, once = 0;
        for(int x:arr)
        {
            once = ~twice&(once^x);
            twice = ~once&(twice^x);
        }
        return once;
    }
}
