package com.hwt.notes.io.file;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Md5FileStream {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        File file = new File("C:\\Users\\hong.wentao\\Desktop\\ftp.doc");
        File zfile = new File("C:\\Users\\hong.wentao\\Desktop\\c.zip");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            getMd5(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ZipFile zipFile = new ZipFile(zfile);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                if ("ftp.doc".equals(zipEntry.getName())) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    int readLen = 0;
                    byte[] buffer = new byte[1024];
                    while((readLen = inputStream.read(buffer))!=-1){
                        bos.write(buffer, 0, readLen);
                    }
                    inputStream.close();
                    bos.close();
                    byte[] bytes = bos.toByteArray();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    getMd5(byteArrayInputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getMd5(InputStream inputStream) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        try {
            int readLen = 0;
            byte[] buffer = new byte[1024];
            while((readLen = inputStream.read(buffer))!=-1){
                md5.update(buffer, 0, readLen);
            }
            BigInteger bigInteger = new BigInteger(1, md5.digest());
            System.out.println(bigInteger.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
