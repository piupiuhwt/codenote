package com.hwt.notes.pattern.struct.facade;

public class FacadeMain {
    /**
     * 对外观者进行简单使用
     */
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.descPrint();
        System.out.println("-----------------------------------");
        facade.print2And1();
    }
}
