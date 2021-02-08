package com.hwt.notes.pattern.action.command;

import lombok.Data;

import java.util.Collection;

@Data
public class Invoker {
    //命令
    private Command command;

    //命令下发者命令集
    private Collection<Command> commands;

    /**
     * 我不需要管命令过程具体怎么实现
     * 我只知道我下发了个任务清单，只要按照命令完成就行
     */
    public void call() {
        System.out.println("指挥者下发命令");
        command.execute();
    }

    public void callCommandsLogicOrder(){
        //命令集执行逻辑顺序调整执行
    }
}
