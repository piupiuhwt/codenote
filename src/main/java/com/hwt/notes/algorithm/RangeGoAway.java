package com.hwt.notes.algorithm;

import lombok.Data;

import static com.hwt.notes.utils.AssertUtil.isNull;

/**
 * 有一群小朋友 循环报数 数到3的人出圈
 */
public class RangeGoAway {
    public static void main(String[] args) {
        int[] array = new int[5];
        fillIntArrayByIndex(array);
        SingleLink singleLink = arrayToLink(array);
        int num = 0;
        while(singleLink.length>1) {
            SingleLink.Node node = singleLink.removeHead();
            num++;
            if(num == 3){
                num = 0;
                continue;
            }
            singleLink.insert(node);
        }
        SingleLink.Node node = singleLink.removeHead();
        System.out.println(node.getValue());
    }

    public static void fillIntArrayByIndex(int[] array){
        if (isNull(array)) return;
        int length = array.length;
        //TODO stream 底层原理需要了解
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
    }

    public static SingleLink arrayToLink(int[] array){
        SingleLink link = new SingleLink();
        for (int value : array) {
            SingleLink.Node node = new SingleLink.Node();
            node.setValue(value);
            link.insert(node);
        }
        return link;
    }

    static class SingleLink{
        public Node head;
        public Node tail;
        public int length = 0;

        @Data
        public static class Node{
            private Node next;
            private int value;
        }

        public void insert(Node node){
            if (isNull(node)) {
                return;
            }
            if(head != null && tail != null){
                tail.setNext(node);
                tail = node;
            }
            if (isNull(head)) {
                head = node;
            }
            if (isNull(tail)) {
                tail = node;
            }
            length++;
        }

        public Node removeHead(){
            Node next = head.getNext();
            Node node = head;
            head.setNext(null);
            head = next;
            if (tail == node) {
                tail = null;
            }
            length--;
            return node;
        }
    }
}
