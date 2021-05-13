package com.hwt.notes.jdbc;

import com.hwt.notes.jdbc.annotation.ShouldConvert;
import com.hwt.notes.jdbc.annotation.TestBean;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.hwt.notes.jvm.reflect.ReflectionHelper.camelCaseToSnakeCase;

public class BeanSqlUtil {

    public static String getUpdateSql(Object instance){
        List<String> fieldValueList = new LinkedList<>();
        process(instance,(fieldName,value) -> {
            fieldValueList.add(fieldName+"="+wrapper(value.toString()));
        });
        String fieldValueString = String.join(",", fieldValueList);
        if("".equals(fieldValueString.trim())){
            return null;
        }
        return "set "+fieldValueString;
    }

    public static String getInsertSql(Object instance){
        List<String> dbFieldList = new LinkedList<>();
        List<String> valueList = new LinkedList<>();
        process(instance,(fieldName,value) -> {
            dbFieldList.add(wrapper(fieldName));
            valueList.add(wrapper(value.toString()));
        });
        String fieldsString = String.join(",", dbFieldList);
        String valuesString = String.join(",", valueList);
        if("".equals(fieldsString.trim()) || "".equals(valuesString.trim())){
            return null;
        }
        return "("+fieldsString+") VALUES("+valuesString+")";
    }

    public static<T> T getResultBean(Class<T> clazz, ResultSet resultSet) throws Exception {
        T resultBean = clazz.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ShouldConvert shouldConvert = declaredField.getAnnotation(ShouldConvert.class);
            if (shouldConvert == null) {
                continue;
            }
            Class<?> type = declaredField.getType();
            Object value = getValue(resultSet, camelCaseToSnakeCase(declaredField.getName()), type);
            declaredField.set(resultBean, value);
        }
        return resultBean;
    }

    private static Object getValue(ResultSet resultSet, String fieldName, Class<?> type) throws SQLException {
        switch (type.toString()) {
            case "int":
            case "class java.lang.Integer": {
                return resultSet.getInt(fieldName);
            }
            case "boolean":
            case "class java.lang.Boolean": {
                return resultSet.getBoolean(fieldName);
            }
            case "String":
            case "class java.lang.String": {
                return resultSet.getString(fieldName);
            }
            case "long":
            case "class java.lang.Long": {
                return resultSet.getLong(fieldName);
            }
        }
        return null;
    }

    public static void process(Object instance,FieldsHandler fieldsHandler){
        Class<?> processClazz = instance.getClass();
        Field[] declaredFields = processClazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            ShouldConvert shouldConvert = declaredField.getAnnotation(ShouldConvert.class);
            if (shouldConvert == null) {
                continue;
            }
            String fieldName = camelCaseToSnakeCase(declaredField.getName());
            try {
                Object value = declaredField.get(instance);
                fieldsHandler.processFieldsAndInstance(fieldName, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String wrapper(String value){
        return "'" + value + "'";
    }

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        System.out.println(getInsertSql(testBean));
        System.out.println(getUpdateSql(testBean));
    }

}
