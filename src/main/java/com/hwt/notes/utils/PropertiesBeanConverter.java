package com.hwt.notes.utils;

import lombok.ToString;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

@ToString
@Data
public class PropertiesBeanConverter implements BeanConverter {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesBeanConverter.class);

    private Properties properties;

    private boolean couldConvert = false;

    public PropertiesBeanConverter() {
        properties = new Properties();
    }

    @Override
    public <T> T convertToBean(Class<T> beanClazz) {
        if (!couldConvert) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        PropertiesBeanConverter propertiesBeanConverter = new PropertiesBeanConverter();
        InputStream aStream = PropertiesBeanConverter.class.getClassLoader().getResourceAsStream("a.properties");
        if (!propertiesBeanConverter.loadFile(aStream)) {
            return;
        }
        Properties properties = propertiesBeanConverter.getProperties();
        properties.put("cc", "22");
        String path = PropertiesBeanConverter.class.getClassLoader().getResource("a.properties").getPath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path),false);
            // 存储时使用中文会用 6个 字符存储 0、1 分别是 \ u 后面时4个16进制字符
            properties.store(fileOutputStream,"嘿嘿嘿   aaaaa");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试load方法是否是追加方式
     * 结果为追加
     */
    public static void testPropertiesLoad(){
        PropertiesBeanConverter propertiesBeanConverter = new PropertiesBeanConverter();
        String property = System.getProperty("user.dir");
        System.out.println(property);
        System.out.println(System.class.getResource("/").getPath());
        InputStream aStream = PropertiesBeanConverter.class.getClassLoader().getResourceAsStream("a.properties");
        InputStream bStream = PropertiesBeanConverter.class.getClassLoader().getResourceAsStream("b.properties");
        propertiesBeanConverter.loadFile(aStream);
        System.out.println(propertiesBeanConverter.getProperties());
        propertiesBeanConverter.loadFile(bStream);
        System.out.println(propertiesBeanConverter.getProperties());
    }

    public boolean loadFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.getName().endsWith(".properties")) {
            return false;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            return loadFile(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("文件获取流发生异常{}",file.getAbsolutePath());
        }
        return false;
    }

    private boolean loadFile(InputStream inputStream) {
        try {
            properties.load(inputStream);
            couldConvert = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}