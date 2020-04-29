package com.study.majinhu.designModel.FacadePattern;

/**
 * @ClassName Shape
 * @Description 门面模式，外观模式
 * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 * 这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 * https://www.runoob.com/design-pattern/facade-pattern.html
 * @Author majinhu
 * @Date 2019/12/25 9:00
 * @Version 1.0
 **/
public interface Shape {
    void draw();
}
