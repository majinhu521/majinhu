package com.study.majinhu.designModel.Factory;

/**
 * @ClassName Circle
 * @Description
 * @Author majinhu
 * @Date 2019/6/12 16:40
 * @Version 1.0
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}