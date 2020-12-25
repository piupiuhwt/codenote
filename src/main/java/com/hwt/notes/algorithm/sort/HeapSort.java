package com.hwt.notes.algorithm.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> {
    public static void main(String[] args) {
        String[] testArray = {"123","23","4345","674","098","34","21","06","s2","p3","b4","dddd"};
        System.out.println(5 >> 1);
        HeapSort<String> heapSorter = new HeapSort<>();
        heapSorter.sort(testArray);
        for(int i=0;i<testArray.length;i++){
            System.out.println(testArray[i]);
        }
    }

    public void sort(T[] sortArray){
        if (sortArray == null || sortArray.length <=1) {
            return;
        }
        //1. 构建堆
        initHeap(sortArray);
        swapAndShiftDown(sortArray,0,sortArray.length-1);
    }


    private void initHeap(T[] sortArray){
        for(int i = 1; i < sortArray.length; i++){
            int tempPos = i;
            int parentPos;
            while(true){
                parentPos = tempPos-1;
                if(parentPos<0){
                    break;
                }
                parentPos = parentPos>>1;
                if (sortArray[parentPos].compareTo(sortArray[tempPos])>=0) {
                    break;
                }
                swap(sortArray,parentPos,tempPos);
                tempPos=parentPos;
            }
        }
        System.out.println(Arrays.toString(sortArray));
    }

    //4. 重复执行2，3
    private void swapAndShiftDown(T[] sortArray,int start,int tail){
        //2. 交换首尾
        swap(sortArray,start, tail);
        tail--;
        if (tail - start <= 0) {
            return;
        }
        //3. 做shift_down
        shiftDown(sortArray, start, tail);
        swapAndShiftDown(sortArray, start, tail);
    }

    private void swap(T[] sortArray,int position1,int position2){
        T temp = sortArray[position1];
        sortArray[position1] = sortArray[position2];
        sortArray[position2] = temp;
    }

    private void shiftDown(T[] sortArray,int start,int tail){
        int right = (start+1) << 1;
        int left = right-1;
        int changeToPos = -1;
        if(left>tail){
            return;
        }
        if(right>tail && sortArray[left].compareTo(sortArray[start])>0){
            swap(sortArray, left, start);
            changeToPos = left;
        }
        if(right<=tail){
            int bigger = sortArray[left].compareTo(sortArray[right])>0?left:right;
            if (sortArray[bigger].compareTo(sortArray[start])>0) {
                swap(sortArray, bigger, start);
                changeToPos = bigger;
            }
        }
        if(changeToPos>0){
            shiftDown(sortArray,changeToPos,tail);
        }
    }
}
