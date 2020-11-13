package com.study.majinhu.designModel.DecoratorPattern;

/**
 * @ClassName Rectangle
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 9:38
 * @Version 1.0
 **/
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
