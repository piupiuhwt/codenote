package com.hwt.notes.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class UnsafeLinkedSetQueue<T> {
    private final LinkedHashSet<T> queue;

    public UnsafeLinkedSetQueue(LinkedHashSet<T> queue){
        this.queue = queue;
    }

    public void poll(T elemet){
        queue.add(elemet);
    }

    public T take(){
        Iterator<T> iterator =  queue.iterator();
        if (iterator.hasNext()) {
            T head = iterator.next();
            iterator.remove();
            return head;
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.iterator();
    }
}
