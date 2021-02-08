package com.hwt.notes.pattern.action.command;

import lombok.Data;

@Data
public class Receiver {


    public void action(){
        System.out.println("接收者接到命令");
        System.out.println("执行行动");
    }
}
