package com.hwt.notes.io.poi;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.io.HWPFFileSystem;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * 1     读docx文件
 *
 * 1.1     通过XWPFWordExtractor读
 *
 * 1.2     通过XWPFDocument读
 *
 * 2     写docx文件
 *
 * 2.1     直接通过XWPFDocument生成
 *
 * 2.2     以docx文件作为模板
 */
public class DocTest {
    public static void main(String[] args) {
        docTest();
    }

    public static void docTest(){
        String userprofile = System.getenv("USERPROFILE");
        System.out.println(userprofile);
        File docFile = new File(userprofile + "/Desktop/ftp.doc");
        try {
            FileInputStream inputStream = new FileInputStream(docFile);
//            Biff8EncryptionKey.setCurrentUserPassword("pass");
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            HWPFDocument hwpfDocument = new HWPFDocument(fs.getRoot());
            WordExtractor wordExtractor = new WordExtractor(hwpfDocument);
            System.out.println(wordExtractor.getText());
//            HWPFDocument hwpfDocument = new HWPFDocument(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void docxTest(){
        String userprofile = System.getenv("USERPROFILE");
        System.out.println(userprofile);
        File docFile = new File(userprofile + "/Desktop/zzz.docx");
        try {
            FileInputStream inputStream = new FileInputStream(docFile);
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            EncryptionInfo info = new EncryptionInfo(fs);
            System.out.println(info.isDocPropsEncrypted());
            Decryptor d = Decryptor.getInstance(info);
            String password = "123";
            try {
                if (!d.verifyPassword(password)) {
                    throw new RuntimeException("Unable to process: document is encrypted");
                }
                InputStream dataStream = d.getDataStream(fs);
                XWPFDocument document = new XWPFDocument(dataStream);
                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(document);
                System.out.println(xwpfWordExtractor.getText());
                List<XWPFTable> tables = document.getTables();
                for (XWPFTable table : tables) {
                    System.out.println(table.getText());
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void xlsxTest(){
    }
}
