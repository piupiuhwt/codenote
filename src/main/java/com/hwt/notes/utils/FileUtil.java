package com.hwt.notes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws NoSuchFileException, FileNotFoundException {
        readFile();
        System.out.println(System.getProperty("user.dir"));
    }
}
