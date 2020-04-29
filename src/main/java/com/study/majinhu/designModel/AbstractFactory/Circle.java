package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Circle
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:26
 * @Version 1.0
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}