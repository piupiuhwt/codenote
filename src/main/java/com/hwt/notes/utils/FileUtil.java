package com.hwt.notes.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

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
        readFile();
        System.out.println(System.getProperty("user.dir"));
    }
}
