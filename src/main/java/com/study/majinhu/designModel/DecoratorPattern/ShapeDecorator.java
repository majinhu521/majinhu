package com.study.majinhu.designModel.DecoratorPattern;

/**
 * @ClassName ShapeDecorator
 * @Description 创建实现了 Shape 接口的抽象装饰类。
 * @Author majinhu
 * @Date 2020/5/18 9:40
 * @Version 1.0
 **/
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
