package com.hwt.notes.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.Data;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

@Data
public class HttpsUtils {
    public static String baseUrl = "https://cdn.scaleflex.it/demo/360-car/iris-";
    public static String urlSuffix = ".jpeg";

    MyX509TrustManager xtm = new MyX509TrustManager();
    MyHostnameVerifier hnv = new MyHostnameVerifier();
    String url;
    String contentType;

    public HttpsUtils(String HttpsUrl) {
        this.url = HttpsUrl;
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };
            sslContext.init( null,
                    xtmArray,
                    new java.security.SecureRandom() );
        } catch(GeneralSecurityException gse ) {
        }
        if( sslContext != null ) {
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    sslContext.getSocketFactory() );
        }
        HttpsURLConnection.setDefaultHostnameVerifier( hnv );
    }

    public byte[] getResult() {
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        byte[] tempBytes = new byte[1024 * 1024];
        try {
            URLConnection urlCon = (new URL(url)).openConnection();
            contentType = urlCon.getContentType();
            InputStream inputStream = urlCon.getInputStream();
            int len = 0;
            while ((len = inputStream.read(tempBytes))!=-1){
                resultStream.write(tempBytes,0,len);
            }
            //   增加自己的代码
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        byte[] resultBytes = resultStream.toByteArray();
        try {
            resultStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBytes;
    }

    class MyX509TrustManager implements X509TrustManager {
        public MyX509TrustManager(){}
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
        public void checkServerTrusted(X509Certificate[] chain,String authType) {
        }
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }


    class MyHostnameVerifier implements HostnameVerifier {
        public MyHostnameVerifier(){}
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static void main(String[] args) {
        HttpsUtils httpsUtils = new HttpsUtils(baseUrl + 1 + urlSuffix);
        byte[] result = httpsUtils.getResult();
    }

}
