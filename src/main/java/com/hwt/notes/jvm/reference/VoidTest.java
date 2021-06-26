package com.hwt.notes.jvm.reference;



public class VoidTest {
    interface FCC<R> {
        R call();
    }
    class ZZ implements  FCC<Void>{

        @Override
        public Void call() {
            return null;
        }
    }
    public static void main(String[] args) {

    }
}
