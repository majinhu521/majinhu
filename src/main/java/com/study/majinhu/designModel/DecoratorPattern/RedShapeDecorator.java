package com.study.majinhu.designModel.DecoratorPattern;

/**
 * @ClassName RedShapeDecorator
 * @Description 创建扩展了 ShapeDecorator 类的实体装饰类。
 * @Author majinhu
 * @Date 2020/5/18 9:40
 * @Version 1.0
 **/
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}