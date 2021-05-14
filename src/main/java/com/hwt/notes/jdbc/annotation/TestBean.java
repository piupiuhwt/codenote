package com.hwt.notes.jdbc.annotation;

import lombok.Data;

@Data
public class TestBean {
    @ShouldConvert
    private boolean testFlag = false;

    @ShouldConvert
    private int userAge = 18;

    @ShouldConvert
    private String answerContent = "fdsafsfdasfdsf";
}
