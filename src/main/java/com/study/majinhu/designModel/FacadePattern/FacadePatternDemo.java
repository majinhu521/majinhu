package com.study.majinhu.designModel.FacadePattern;

/**
 * @ClassName FacadePatternDemo
 * @Description
 * @Author majinhu
 * @Date 2019/12/25 9:05
 * @Version 1.0
 **/
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
