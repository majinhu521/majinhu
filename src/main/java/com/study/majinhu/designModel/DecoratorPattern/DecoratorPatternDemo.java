package com.study.majinhu.designModel.DecoratorPattern;

/**
 * @ClassName DecoratorPatternDemo
 * @Description https://www.runoob.com/design-pattern/decorator-pattern.html
 * 我们将创建一个 Shape 接口和实现了 Shape 接口的实体类。
 * 然后我们创建一个实现了 Shape 接口的抽象装饰类 ShapeDecorator，并把 Shape 对象作为它的实例变量。
 *
 * RedShapeDecorator 是实现了 ShapeDecorator 的实体类。
 *
 * DecoratorPatternDemo，我们的演示类使用 RedShapeDecorator 来装饰 Shape 对象。
 * @Author majinhu
 * @Date 2020/5/18 9:41
 * @Version 1.0
 **/
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
