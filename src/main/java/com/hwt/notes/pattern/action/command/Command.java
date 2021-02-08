package com.hwt.notes.pattern.action.command;

/**
 * 不必在乎谁去执行
 * 解耦执行者和命令下发者
 */
public abstract class Command {
    public abstract void execute();
}
