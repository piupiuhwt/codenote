package com.hwt.notes.datastructure.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleLink<T> {

    //头节点
    private Node<T> head = null;
    //尾节点
    private Node<T> tail = null;
    //链长度
    private int length = 0;

    public int length(){
        return this.length;
    }

    ReentrantLock lock = new ReentrantLock();

    public void insert(T value){
        while (true){
            if (!lock.tryLock()) {
                continue;
            }
            Node<T> newNode = new Node<>(null,value);
            length++;
            if(head == null){
                head = newNode;
                tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
            lock.unlock();
            break;
        }
    }

    private void reverseLink(){
        reverseLink(this.head);
    }

    private void reverseLink(Node<T> head){
        while (true) {
            if (!lock.tryLock()) {
                continue;
            }
            Node<T> temp;
            Node<T> resultNext = null;

            while ((temp = head.next) !=null){
                head.next = resultNext;
                resultNext = head;
                head = temp;
            }
            head.next = resultNext;
            lock.unlock();
            break;
        }
    }

    public List<T> getReverseElementArray(){
        ArrayList<T> resultList = new ArrayList<>(length);
        Node<T> temp = head;
        while (temp!=null){
            resultList.add(temp.element);
            temp = temp.next;
        }
        return resultList;
    }




    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node<T> {
        public Node<T> next;
        private T element;
    }

    public static void main(String[] args) {
    }

}
