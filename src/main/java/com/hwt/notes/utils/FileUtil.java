package com.hwt.notes.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.NoSuchFileException;

public class FileUtil {

    public static ClassLoader classLoader = FileUtil.class.getClassLoader();

    public static String getResourceFilePathByName(String fileName) throws NoSuchFileException {
        URL resourceUrl = classLoader.getResource(fileName);
        if (resourceUrl == null) {
            throw new NoSuchFileException("resources has no such file : "+fileName);
        }
        return resourceUrl.getFile();
    }
}
