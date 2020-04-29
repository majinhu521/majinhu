package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Rectangle
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:25
 * @Version 1.0
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}