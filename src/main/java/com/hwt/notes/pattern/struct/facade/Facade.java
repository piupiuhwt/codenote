package com.hwt.notes.pattern.struct.facade;

import com.hwt.notes.pattern.struct.facade.subsystem.Module1;
import com.hwt.notes.pattern.struct.facade.subsystem.Module2;
import com.hwt.notes.pattern.struct.facade.subsystem.Module3;
import lombok.Data;

/**
 * facade pattern 外观模式
 * 符合迪米特法则 引入第三者
 * 简化操作
 * 此模式主要解决的问题是 为复杂的子系统（多个功能模块）提供一个统一简化的外观者
 * 可以类比 网站首页和各个子模块之间的关系
 * 目的是简化使用，是使用者更简洁的做某些事
 */
@Data
public class Facade {
    private Module1 module1;
    private Module2 module2;
    private Module3[] module3 = new Module3[2];

    public Facade(){
        this.module1 = new Module1();
        this.module2 = new Module2();
        this.module3[0] = new Module3();
        this.module3[1] = new Module3();
    }

    public void descPrint(){
        module3[0].print();
        module3[1].print();
        module2.print();
        module1.print();
    }

    public void print2And1(){
        module2.print();
        module1.print();
    }
}
