package com.study.majinhu.designModel.DecoratorPattern;

/**
 * @ClassName Circle
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 9:39
 * @Version 1.0
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}