package com.study.majinhu.designModel.Prototype;

/**
 * @ClassName Square
 * @Description
 * @Author majinhu
 * @Date 2019/8/15 10:12
 * @Version 1.0
 **/
public class Square extends Shape {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
