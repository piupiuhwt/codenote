package com.hwt.notes.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class BinaryToHex {
    public static void main(String[] args) throws IOException {
        String testJpg;
        try {
            testJpg = FileUtil.getResourceFilePathByName("test.jpg");
        } catch (NoSuchFileException e) {
            e.printStackTrace();
            return;
        }
        if (testJpg == null) {
            return;
        }
        File testFile = new File(testJpg);
        byte[] fileBytes = FileUtils.readFileToByteArray(testFile);
        System.out.println(byteArrayToHexString(fileBytes));
    }

    public static String byteToHexString(byte hexByte){
        int hexInt = hexByte & 0xff;
        String hexLow = Integer.toHexString(15 & hexInt);
        String hexHigh = Integer.toHexString((240 & hexInt)>>>4);
        return hexHigh+hexLow;
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hexByte : bytes) {
            sb.append(byteToHexString(hexByte));
        }
        return sb.toString();
    }
}
