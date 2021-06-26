package com.hwt.notes.io.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 移动文件并且覆盖
 */
public class MoveFile {

    public static void main(String[] args) {
        System.out.println(System.getProperties());
        String userprofile = System.getenv("USERPROFILE");
        String filePath1 = userprofile+"\\Desktop\\新建文本文档 (3).txt";
        String filePath2 = userprofile+"\\Desktop\\新建文本文档 (2).txt";
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        System.out.println(file1.exists());
        try {
            //强制执行，会覆盖
//            Files.move(file1.toPath(), file2.toPath(), StandardCopyOption.ATOMIC_MOVE);
            //如果存有相同文件/文件夹会抛出异常(文件已存在)
//            FileUtils.moveFile(file1, file2);
            //文件如果存在在windows系统中不会发生任何事情啥也不干，啥也不是,这个是个native方法
            file1.renameTo(file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}