package com.hwt.notes.thread.threadLocal;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InnerBean {
    private String name;

    private int age;

    private List<String> tags;
}
