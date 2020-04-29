package com.study.majinhu.designModel.Prototype;

/**
 * @ClassName Circle
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 10:13
 * @Version 1.0
 **/
public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}