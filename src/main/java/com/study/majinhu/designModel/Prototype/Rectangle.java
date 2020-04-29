package com.study.majinhu.designModel.Prototype;

/**
 * @ClassName Rectangle
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 10:12
 * @Version 1.0
 **/
public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}