package com.hwt.notes.utils;

import joptsimple.internal.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssertUtil {
    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("fasdf","sdafasf");
        String join = String.join( "\n",strings);
        System.out.println(join);
    }
}
