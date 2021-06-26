package com.hwt.notes.io.file;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZFileOptions;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipRelation {
    public static void main(String[] args) {
        String userprofile = System.getenv("USERPROFILE");
        String deskTopPath = userprofile + "\\desktop\\";

        File a = new File(deskTopPath + "a.rar");
        File b = new File(deskTopPath + "b.7z");
        File c = new File(deskTopPath + "c.zip");
        File d = new File(deskTopPath + "d.doc");

//        try {
//            FileInputStream inputStream = new FileInputStream(d);
//            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
//            System.out.println(zipInputStream.getNextEntry());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        un7zFile(a);
//        unzipFile(deskTopPath,d);
//        unRarFile(a);


        File cccc = new File("C:\\Users\\hong.wentao\\Desktop\\test\11.rar");
        String outPath = "C:\\Users\\hong.wentao\\Desktop\\test";
        String appPath = System.getProperty("user.dir")+"\\utils\\7z\\7z.exe";
        unCompressByCMD(cccc, appPath, outPath);
    }

    public static void unCompressByCMD(File file,String appPath,String outPath){
        String cmd = appPath + " e "+ file.getAbsolutePath() + " -o" + outPath + " -aoa -padf";
        System.out.println(cmd);
        Thread thread = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        });
//        thread1.start();
        try{
            int exitVal = 0;
            Process exec = Runtime.getRuntime().exec(cmd);
            exitVal = exec.waitFor();
            InputStream inputStream = exec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String f;
            while ((f = reader.readLine()) !=null) {
                System.out.println(f);
            }
            System.out.println(exitVal);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void unRarFile(File file){
        try {
            String userprofile = System.getenv("USERPROFILE");
            String deskTopPath = userprofile + "\\desktop\\";
            Archive archive = new Archive(new FileInputStream(file));
            List<FileHeader> fileHeaders = archive.getFileHeaders();
            for (FileHeader fileHeader : fileHeaders) {
                String fileName = fileHeader.getFileNameString();
                System.out.println(fileName);
                fileName = file.getName() + "\\" + fileName;
                InputStream inputStream = archive.getInputStream(fileHeader);
                writeFile(inputStream, fileName, deskTopPath);
            }
        } catch (IOException | RarException e) {
            e.printStackTrace();
        }
    }

    public static void un7zFile(File file){
        try {
            String userprofile = System.getenv("USERPROFILE");
            String deskTopPath = userprofile + "\\desktop\\";
            SevenZFile sevenZFile = new SevenZFile(file, SevenZFileOptions.DEFAULT);
            for (SevenZArchiveEntry sevenZArchiveEntry : sevenZFile.getEntries()) {
                String name = sevenZArchiveEntry.getName();
                System.out.println(name);
                InputStream inputStream = sevenZFile.getInputStream(sevenZArchiveEntry);
                writeFile(inputStream, name, deskTopPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzipFile(String deskTopPath,File file){
        try {
            ZipFile zipFile = new ZipFile(file, Charset.forName("GBK"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                String name = zipEntry.getName();
                System.out.println("name = " + name);
                System.out.println(zipEntry.getSize());
                if (zipEntry.isDirectory()) {
                    new File(deskTopPath + "\\test\\" + name).mkdir();
                    continue;
                }
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                writeFile(inputStream, name, deskTopPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(InputStream inputStream,String name,String deskTopPath){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(new File(deskTopPath + "\\test\\" + name));
            int readLen = 0;
            byte[] buffer = new byte[1024];
            while((readLen = inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer, 0, readLen);
            }
            fileOutputStream.close();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
