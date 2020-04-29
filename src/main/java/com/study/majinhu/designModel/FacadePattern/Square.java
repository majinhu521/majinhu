package com.study.majinhu.designModel.FacadePattern;

/**
 * @ClassName Square
 * @Description
 * @Author majinhu
 * @Date 2019/12/25 9:03
 * @Version 1.0
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}