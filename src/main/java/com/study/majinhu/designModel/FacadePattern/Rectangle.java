package com.study.majinhu.designModel.FacadePattern;

/**
 * @ClassName Rectangle
 * @Description
 * @Author majinhu
 * @Date 2019/12/25 9:02
 * @Version 1.0
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
