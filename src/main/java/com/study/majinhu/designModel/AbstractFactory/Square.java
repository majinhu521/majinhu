package com.study.majinhu.designModel.AbstractFactory;

/**
 * @ClassName Square
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 9:26
 * @Version 1.0
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}