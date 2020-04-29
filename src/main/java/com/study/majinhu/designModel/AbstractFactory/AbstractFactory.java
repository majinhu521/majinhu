package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName AbstractFactory
 * @Description https://www.runoob.com/design-pattern/
 * @Author majinhu
 * @Date 2019/8/15 9:28
 * @Version 1.0
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}