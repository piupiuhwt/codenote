package com.hwt.notes.jvm;


public class SecurityManagerTest {

    static{
        SecurityManager sm=new SecurityManager();
        System.setSecurityManager(sm);
    }

    public static void main(String[] args) {
        SecurityManager securityManager = System.getSecurityManager();
    }
}
