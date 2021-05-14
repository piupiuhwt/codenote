package com.hwt.notes.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象头相关属性
 */
public class ObjectHeader {

    public static void main(String[] args) {
        ClassLayout classLayout = ClassLayout.parseInstance(ObjectHeader.class);
        classLayout.getLossesExternal();

        System.out.println(classLayout.toPrintable());
    }
}
