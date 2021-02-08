package com.hwt.notes.pattern.action.command;

import lombok.Data;

@Data
public class SpecificCommand extends Command{
    private Receiver receiver;

    @Override
    public void execute() {
        System.out.println("具体命令下发过程");
        receiver.action();
    }
}
