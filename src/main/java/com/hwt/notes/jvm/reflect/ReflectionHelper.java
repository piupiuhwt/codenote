package com.hwt.notes.jvm.reflect;

public class ReflectionHelper {

    public static String camelCaseToSnakeCase(String camelCase){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < camelCase.length(); ++i) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }

    public static String snakeCaseToCamelCase(String snakeCase) {
        StringBuilder sb = new StringBuilder(snakeCase.length() + 1);
        boolean capNext = false;

        for(int ctr = 0; ctr < snakeCase.length(); ++ctr) {
            char next = snakeCase.charAt(ctr);
            if (next == '_') {
                capNext = true;
            } else if (Character.isDigit(next)) {
                sb.append(next);
                capNext = true;
            } else if (capNext) {
                sb.append(Character.toUpperCase(next));
                capNext = false;
            } else if (ctr == 0) {
                sb.append(Character.toLowerCase(next));
            } else {
                sb.append(next);
            }
        }
        return sb.toString();
    }
}
