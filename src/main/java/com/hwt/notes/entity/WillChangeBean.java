package com.hwt.notes.entity;

import com.hwt.notes.annotation.WillChange;
import lombok.Data;

@WillChange("toChange")
@Data
public class WillChangeBean {
    public static String testString = "testString";
    private String name;
}
