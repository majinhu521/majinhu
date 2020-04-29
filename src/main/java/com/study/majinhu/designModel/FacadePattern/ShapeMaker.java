package com.study.majinhu.designModel.FacadePattern;

/**
 * @ClassName ShapeMaker
 * @Description
 * ShapeMaker 类使用实体类来代表用户对这些类的调用。
 * @Author majinhu
 * @Date 2019/12/25 9:04
 * @Version 1.0
 **/
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
