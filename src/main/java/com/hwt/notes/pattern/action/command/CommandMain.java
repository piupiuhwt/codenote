package com.hwt.notes.pattern.action.command;

public class CommandMain {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        SpecificCommand command = new SpecificCommand();
        Receiver receiver = new Receiver();
        invoker.setCommand(command);
        command.setReceiver(receiver);

        invoker.call();
    }
}
