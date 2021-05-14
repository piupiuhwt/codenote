package com.hwt.notes.io.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 移动文件并且覆盖
 */
public class MoveFile {

    public static void main(String[] args) {
        String filePath1 = "C:\\Users\\hong.wentao\\Desktop\\新建文本文档 (3).txt";
        String filePath2 = "C:\\Users\\hong.wentao\\Desktop\\新建文本文档 (2).txt";
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        try {
            Files.move(file1.toPath(), file2.toPath(), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}