package com.hwt.notes.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HexToBinary {
    public static void main(String[] args) throws IOException {
        resourcesStringFileToFile("media", "test.txt");
    }

    public static void resourcesStringFileToFile(String resourceFileName,String createFileName) throws IOException {
        String dealPath = FileUtil.getResourceFilePathByName(resourceFileName);
        File deal = new File(dealPath);
        String dealString = FileUtils.readFileToString(deal);
        System.out.println(dealString);
        String[] hexArray = toDoubleCharArray(dealString);
        byte[] byteData = getByteData(hexArray);
        File file = new File(createFileName);
        Path writePath = Files.write(file.toPath(), byteData);
        System.out.println(writePath);
    }

    public static byte getByte(String hexString){
        int len = hexString.length();
        int resultInt = 0;
        for(int i = len-1 ; i >= 0 ; i--){
            char numChar = hexString.charAt(i);
            int digit = Character.digit(numChar, 16);
            int offset = len - i -1;
            if (offset>0) {
                digit = digit << 4 * offset;
            }
            resultInt = resultInt | digit;
        }
        return (byte)resultInt;
    }

    public static String[] splitSpaceToArray(String dealString){
        return dealString.split(" ");
    }

    public static String[] toDoubleCharArray(String dealString){
        int length = dealString.length();
        int times = length/2;
        String[] resultArray = new String[times];
        for(int i = 0;i<times;i++){
            String hexString = String.valueOf(dealString.charAt(2 * i));
            hexString += dealString.charAt(2 * i + 1);
            resultArray[i] = hexString;
        }
        return resultArray;
    }

    public static String rejectSpace(String dealString){
        return dealString.replaceAll(" ","");
    }

    public static byte[] getByteData(String[] hexArray){
        int length = hexArray.length;
        byte[] dataArray = new byte[length];
        for(int i = 0; i < length; i++){
            String hexString = hexArray[i];
            byte hexByte = getByte(hexString);
            dataArray[i] = hexByte;
        }
        return dataArray;
    }

    public static byte[] getByteDataReverse(String[] hexArray){
        int length = hexArray.length;
        byte[] dataArray = new byte[length];
        for(int i = length-1; i >= 0; i--){
            String hexString = hexArray[i];
            byte hexByte = getByte(hexString);
            dataArray[i] = hexByte;
        }
        return dataArray;
    }
}
