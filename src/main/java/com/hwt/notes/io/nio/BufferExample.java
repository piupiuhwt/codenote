package com.hwt.notes.io.nio;

import java.nio.ByteBuffer;

public class BufferExample {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("此时limit：" + buffer.limit());
        System.out.println("此时capacity：" + buffer.capacity());
        System.out.println("此时position：" + buffer.position());
        System.out.println("此时mark：" + buffer.mark());
        System.out.println("=======================================");
//        此时limit：1024
//        此时capacity：1024
//        此时position：0
//        此时mark：java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024]
//        =======================================
        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 56);
        System.out.println("加入三个元素之后的position：" + buffer.position());
        System.out.println("加入三个元素之后的limit：" + buffer.limit());
        System.out.println("=======================================");
//        加入三个元素之后的position：3
//        =======================================
        buffer.flip();
        System.out.println("执行flip之后的limit：" + buffer.limit());
        System.out.println("执行flip之后的position：" + buffer.position());
        System.out.println("取出的第一个元素：" + buffer.get() + " 且此时position：" + buffer.position());
        System.out.println("remaining的值为：" + buffer.remaining()+" 表示position和limit之间的值的个数");
        System.out.println("=======================================");
//        执行flip之后的limit：3
//        执行flip之后的position：0
//        取出的第一个元素：97 且此时position：1
//        =======================================
        buffer.clear();
        System.out.println("执行clear后position和limit的位置：" + buffer.position() + " " + buffer.limit());
        System.out.println("执行之后，缓冲区并不清除：获取第三个元素：" + buffer.get(2));
        System.out.println("此时的第一个元素：" + buffer.get(0));
//        执行clear后position和limit的位置：0 1024
//        执行之后，缓冲区并不清除：获取第三个元素：56
//        此时的第一个元素：97
    }
}
