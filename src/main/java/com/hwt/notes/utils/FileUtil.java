package com.hwt.notes.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class FileUtil {

    public static ClassLoader classLoader = FileUtil.class.getClassLoader();

    public static String getResourceFilePathByName(String fileName) throws NoSuchFileException {
        URL resourceUrl = classLoader.getResource(fileName);
        if (resourceUrl == null) {
            throw new NoSuchFileException("resources has no such file : "+fileName);
        }
        return resourceUrl.getFile();
    }

    public static void readFile() throws NoSuchFileException, FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File(getResourceFilePathByName("deal"))));
        scanner.useDelimiter("\\a");
        String next = scanner.next();
        Scanner scanner1 = scanner = new Scanner(System.in);
        scanner1.useDelimiter("\\a");
        System.out.println(scanner1.next());
        System.out.println(next);
    }

    public static void bytesToFile(String filePath,byte[] bytes){
        try(FileOutputStream fos = new FileOutputStream(filePath)){
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFileException, FileNotFoundException {
        String xlPath = "D:\\迅雷下载";
        File file = new File(xlPath);
        for (File listFile : file.listFiles()) {
            if (!listFile.isDirectory()) {
                continue;
            }
            moveFiles(listFile);
        }
    }

    public static void moveFiles(File dir){
        for (File file1 : dir.listFiles()) {
            if (file1.isDirectory()) {
                moveFiles(file1);
                continue;
            }
            if (file1.getName().endsWith(".mp4") || file1.getName().endsWith(".MP4")) {
                try {
                    FileUtils.moveFile(file1,new File("D:\\暂存\\新建文件夹 (3)\\all\\"+file1.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
